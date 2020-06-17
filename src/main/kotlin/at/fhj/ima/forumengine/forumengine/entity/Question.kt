package at.fhj.ima.forumengine.forumengine.entity

import com.sun.scenario.effect.Identity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table
class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var questId: Int,
        @Column
        var title: String = "",
        var text: String = "",
        var postedOn: LocalDate? = null,
        var hochwaehlis: Int = 0,
        var runterwaehlis: Int = 0,
        @ManyToOne
        @JoinColumn(name = "forumId")
        var forum: Forum,
        @OneToMany(mappedBy = "question")
        var answers: MutableList<Answer>
) {

}