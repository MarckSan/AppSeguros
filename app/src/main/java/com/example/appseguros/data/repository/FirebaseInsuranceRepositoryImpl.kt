package com.example.appseguros.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.input.key.key
import androidx.core.view.children
import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.data.model.PdfData
import com.example.appseguros.data.model.User
import com.example.appseguros.utilitiesGeneral.UtilitiesFun
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FirebaseInsuranceRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : InsuranceRepository {
    private val productsRef = database.getReference("insuranceProducts")
    private val contractedRef = database.getReference("contractedInsurances")
    private val usersRef = database.getReference("users")
    private val pdfRef = database.getReference("pdfUrl")

    override suspend fun getInsuranceProducts(): List<InsuranceProduct> {
        val snapshot = productsRef.get().await()
        val lista : List<InsuranceProduct> = snapshot.children.mapNotNull { childSnapshot ->
            childSnapshot.getValue(InsuranceProduct::class.java)?.copy(id = childSnapshot.key ?: "")
        }
        return lista
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
        val userId = UtilitiesFun.extractUsernameSplit(user.email?: "")
        return userId?.let {
            val task = usersRef.child(it).setValue(user.copy(id = it))
            try {
                task.await() // Espera a que la tarea se complete
                task.isSuccessful // Verifica el resultado DESPUÉS de que la tarea se complete
            } catch (e: Exception) {
                // Manejo de errores si la tarea falla
                println("Error al guardar el usuario: ${e.message}")
                false
            }
        } ?: false
    }

    override suspend fun loginUser(email: String, pass: String): User? {
        //val snapshot = usersRef.orderByChild("email").equalTo(email).get().await() //Busca por el correo
        val snapshot = usersRef.child(UtilitiesFun.extractUsernameSplit(email)).get().await()
        val user = snapshot.getValue(User::class.java)
        return user?.takeIf { it.pass == pass } //Verifica la contraseña
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