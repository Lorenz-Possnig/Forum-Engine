package at.fhj.ima.forumengine.forumengine.entity

import javax.persistence.*
import javax.xml.bind.DatatypeConverter

@Entity
class PersistedImage(
        @field:Id
        @field:GeneratedValue
        var id : Long = 0,
        @field: Lob
        var bytes : ByteArray? = null,
        var mime : String = "",
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name="profilePicture")
        var user: User
) {
    fun toStreamingURI(): String {
        val base64 = DatatypeConverter.printBase64Binary(bytes)
        return "data:$mime;base64,$base64"
    }





    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersistedImage

        if (id != other.id) return false
        if (mime != other.mime) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + mime.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }


}