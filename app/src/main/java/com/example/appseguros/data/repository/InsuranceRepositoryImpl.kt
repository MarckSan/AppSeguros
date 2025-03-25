package com.example.appseguros.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.data.model.InsuranceType
import com.example.appseguros.data.model.PdfData
import com.example.appseguros.data.model.User
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

class InsuranceRepositoryImpl @Inject constructor(
    // Aquí irían las dependencias necesarias, por ejemplo:
    // private val apiService: ApiService,
    // private val userDao: UserDao
) : InsuranceRepository {
    override suspend fun getInsuranceProducts(): List<InsuranceProduct> {
        // Aquí iría la lógica para obtener los productos de seguros.
        // Ejemplo:
        // return apiService.getInsuranceProducts()
        return listOf(
            InsuranceProduct(1,"Seguro de Auto", "Descripcion de seguro de auto",
                imageUrl = "https://example.com/auto-insurance.jpg",
                type = InsuranceType.AUTO ),
            InsuranceProduct(2,"Seguro de Hogar", "Descripcion de seguro de hogar",imageUrl = "https://example.com/auto-insurance.jpg",
                type = InsuranceType.AUTO),
            InsuranceProduct(3,"Seguro de Vida", "Descripcion de seguro de vida",imageUrl = "https://example.com/auto-insurance.jpg",
                type = InsuranceType.AUTO),
        )
    }

    override suspend fun saveUser(user: User): Boolean {
        // Aquí iría la lógica para guardar el usuario.
        // Ejemplo:
        // apiService.saveUser(user)
        // userDao.insert(user)
        println("Usuario guardado: $user")
        return true
    }

    override suspend fun loginUser(email: String, pass: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun getPdfUrl(): PdfData {
        // Aquí iría la lógica para obtener la URL del PDF.
        // Ejemplo:
        // return apiService.getPdfUrl(insuranceId)
        return PdfData("URL del PDF", "Nombre del PDF")
    }

    override suspend fun contractInsurance(userId: Int, insuranceId: Int) {
        // Aquí iría la lógica para contratar un seguro.
        // Ejemplo:
        // apiService.contractInsurance(userId, insuranceId)
        println("Usuario: $userId a contratado el seguro: $insuranceId")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getContractedInsurances(userId: Int): List<ContractedInsurance> {
        // Aquí iría la lógica para obtener los seguros contratados.
        // Ejemplo:
        // return apiService.getContractedInsurances(userId)

        return listOf(
            //ContractedInsurance(1, "Seguro de Auto", "2024-05-01",),
            //ContractedInsurance(2, "Seguro de Hogar", "2024-05-15")
            contractedInsurance
        )
    }

    override suspend fun cancelInsurance(insuranceId: Int) {
        // Aquí iría la lógica para cancelar un seguro.
        // Ejemplo:
        // apiService.cancelInsurance(insuranceId)
        println("Se cancelo el seguro: $insuranceId")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCancelledInsurances(userId: Int): List<ContractedInsurance> {
        // Aquí iría la lógica para obtener los seguros cancelados.
        // Ejemplo:
        // return apiService.getCancelledInsurances(userId)
        return listOf(
            //ContractedInsurance(3, "Seguro de Vida", "2024-04-20", "2024-05-01")
            contractedInsurance
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val user = User(
        id = 1,
        name = "Juan",
        lastName = "Pérez",
        secondLastName = "Gómez",
        birthDate = LocalDate.of(1990, Month.JANUARY, 15),
        phoneNumber = "5512345678",
        email = "juan.perez@example.com",
        pass = "contraseña123"
    )

    // Crear un objeto InsuranceProduct
    val insuranceProduct = InsuranceProduct(
        id = 101,
        name = "Seguro de Auto",
        description = "Seguro de cobertura total para tu auto",
        imageUrl = "https://example.com/auto-insurance.jpg",
        type = InsuranceType.AUTO
    )

    // Crear un objeto ContractedInsurance
    @RequiresApi(Build.VERSION_CODES.O)
    val contractedInsurance = ContractedInsurance(
        id = 1001,
        user = user,
        product = insuranceProduct,
        startDate = LocalDate.of(2024, Month.MAY, 1),
        endDate = LocalDate.of(2025, Month.APRIL, 30),
        policyPdfUrl = "https://example.com/policy1001.pdf",
        isCancelled = false // O true si está cancelado
    )

}