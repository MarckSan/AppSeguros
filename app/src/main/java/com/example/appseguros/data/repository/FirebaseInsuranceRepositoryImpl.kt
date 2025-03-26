package com.example.appseguros.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.data.model.PdfData
import com.example.appseguros.data.model.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FirebaseInsuranceRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : InsuranceRepository {
    private val productsRef = database.getReference("products")
    private val contractedRef = database.getReference("contractedInsurances")
    private val usersRef = database.getReference("users")
    private val pdfRef = database.getReference("pdfUrl")

    override suspend fun getInsuranceProducts(): List<InsuranceProduct> {
        val snapshot = productsRef.get().await()
        return snapshot.children.mapNotNull {
            it.getValue(InsuranceProduct::class.java)
        }
    }

    override suspend fun getContractedInsurances(userId: Int): List<ContractedInsurance> {
        val snapshot = contractedRef.orderByChild("userId").equalTo(userId.toDouble()).get().await()
        return snapshot.children.mapNotNull {
            it.getValue(ContractedInsurance::class.java)
        }
    }

    override suspend fun getCancelledInsurances(userId: Int): List<ContractedInsurance> {
        val snapshot = contractedRef.orderByChild("userId").equalTo(userId.toDouble()).get().await()
        return snapshot.children.mapNotNull { it ->
            it.getValue(ContractedInsurance::class.java)?.takeIf { it.isCancelled }
        }
    }

    override suspend fun saveUser(user: User): Boolean {
        val userId = usersRef.push().key
        return userId?.let {
            usersRef.child(it).setValue(user.copy(id = it)).isSuccessful
        } ?: false
    }

    override suspend fun loginUser(email: String, pass: String): User? {
        val snapshot = usersRef.orderByChild("email").equalTo(email).get().await()
        val user = snapshot.children.mapNotNull {
            it.getValue(User::class.java)
        }.firstOrNull()
        return user?.takeIf { it.pass == pass }
    }

    override suspend fun getPdfUrl(): PdfData {
        val snapshot = pdfRef.get().await()
        return snapshot.getValue(PdfData::class.java) ?: PdfData("","")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun contractInsurance(userId: Int, productId: Int) {
        val insuranceId = contractedRef.push().key
        val contractedInsurance = ContractedInsurance(
            id = insuranceId,
            userId = userId.toString(),
            productId = productId.toString(),
            startDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
            endDate = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_DATE),
            policyPdfUrl = "url",
            isCancelled = false
        )
        insuranceId?.let {
            contractedRef.child(it).setValue(contractedInsurance)
        }
    }

    override suspend fun cancelInsurance(insuranceId: String) {
        val updates = mapOf<String, Any>("cancelled" to true)
        contractedRef.child(insuranceId).updateChildren(updates)
    }
}