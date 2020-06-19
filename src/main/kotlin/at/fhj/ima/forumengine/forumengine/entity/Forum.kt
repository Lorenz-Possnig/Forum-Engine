package at.fhj.ima.forumengine.forumengine.entity

import java.io.Serializable
import java.math.BigInteger
import javax.persistence.*

@Entity(name="Forum")
@Table(uniqueConstraints = [UniqueConstraint(name="name", columnNames = ["name"])])
class Forum(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var forumId: Int? = null,
        @Column
        var name: String = "",
        var description: String = "",
        @OneToMany(mappedBy = "forum", orphanRemoval = true, cascade = [CascadeType.ALL])
        var questions: MutableList<Question> = mutableListOf(),
        @ManyToOne
        @JoinColumn(name = "userId")
        var createdBy: User? = null,
        var frontImage: String = ""
) {
        fun getUsername(): String = createdBy?.username?:"unknown"

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Forum

                if (forumId != other.forumId) return false
                if (name != other.name) return false
                if (description != other.description) return false
                if (questions != other.questions) return false
                if (createdBy != other.createdBy) return false

                return true
        }

        override fun hashCode(): Int {
                var result = forumId?.hashCode() ?: 0
                result = 31 * result + name.hashCode()
                result = 31 * result + description.hashCode()
                result = 31 * result + questions.hashCode()
                result = 31 * result + createdBy.hashCode()
                return result
        }

        override fun toString(): String {
                return "Forum(forumId=$forumId, name='$name', description='$description', questions=$questions, createdBy=$createdBy)"
        }
}