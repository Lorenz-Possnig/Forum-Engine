package at.fhj.ima.forumengine.forumengine.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table
class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var answId: Int? = null,
        @Column
        var text: String = "",
        var hochwaehlis: Int = 0,
        var runterwaehlis: Int = 0,
        @ManyToOne
        var question: Question? = null,
        @ManyToOne
        @JoinColumn(name = "userId")
        var createdBy: User? = null
) {
    fun getUsername(): String = createdBy?.username?:"unknown"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Answer

        if (answId != other.answId) return false
        if (text != other.text) return false
        if (hochwaehlis != other.hochwaehlis) return false
        if (runterwaehlis != other.runterwaehlis) return false
        if (question != other.question) return false

        return true
    }

    override fun hashCode(): Int {
        var result = answId?.hashCode() ?: 0
        result = 31 * result + text.hashCode()
        result = 31 * result + hochwaehlis
        result = 31 * result + runterwaehlis
        result = 31 * result + (question?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Answer(answId=$answId, text='$text', hochwaehlis=$hochwaehlis, runterwaehlis=$runterwaehlis, question=$question)"
    }
}