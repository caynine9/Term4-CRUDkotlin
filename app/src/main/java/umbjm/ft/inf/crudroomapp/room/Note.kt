package umbjm.ft.inf.crudroomapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val NIM: String,
    val Nama: String,
    val Alamat_lengkap: String,
    val No_hp: String
)
