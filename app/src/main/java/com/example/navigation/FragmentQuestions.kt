package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigation.databinding.FragmentQuestionsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentQuestions.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentQuestions : Fragment() {

    data class Question(val image: Int, val question: String, val answers: List<String>, val correctOne: Int)

    private val questions: MutableList<Question> = mutableListOf(
        Question(R.drawable.harry_potter, "¿Quién mata a la serpiente de Lord Voldemort?", listOf("Neville Longbottom","Harry Potter","Ron Weasley","Luna Lovegood"), 0),
        Question(R.drawable.back_to_the_feature, "¿A qué año viajan Marty y Doc en Regreso al futuro II?", listOf("2005","2010","2015","2000"), 2),
        Question(R.drawable.star_wars, "¿En qué año se estrenó la primera película de Star Wars?", listOf("1975","1984","1980","1977"), 3),
        Question(R.drawable.inception, "¿Cuál es el director de la película Origen, estrenado en el 2010 y protagonizado por Leonardo DiCaprio?", listOf("Todd Phillips","James Cameron","Christopher Nolan","Martin Scorsese"), 2),
        Question(R.drawable.el_viaje_de_chihiro, "En el Viaje de Chihiro (2001) En qué se convierten sus papás por comer en el pueblo abandonado?", listOf("Dragones","Tortugas","Sapos","Cerdos"), 3),
        Question(R.drawable.the_mummy, "¿Bajo qué estatua se encontraba el sarcófago de Imhotep en La Momia?", listOf("Osiris","Anubis","Horus","Isis"), 1)
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size
    private lateinit var binding: FragmentQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false)
        randomizeQuestions()

        binding.game = this
        binding.btnSendAnswer.setOnClickListener {
            val checkedId = binding.groupAnswers.checkedRadioButtonId
            // Checkeamos si se ha seleccionado alguna opción, si no, no se hace nada
            if (-1 != checkedId) {
                binding.groupAnswers.clearCheck()
                var answerIndex = 0
                when (checkedId) {
                    R.id.answerTwo -> answerIndex = 1
                    R.id.answerThree -> answerIndex = 2
                    R.id.answerFour -> answerIndex = 3
                }
                // Revisamos si la respuesta seleccionada por el usuario corresponde a la correcta
                if (answerIndex == currentQuestion.correctOne) {
                    questionIndex++
                    // Avanzamos a la siguiente pregunta
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // Si ha ganado vamos al fragmento de FragmentWon.
                    }
                } else {
                    // Si ha contestado alguna pregunta mal, lo llevará al FragmentTryAgain.
                }
            }
        }
        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        binding.imageQuestion.setImageResource(currentQuestion.image)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_movies_trivia_question, questionIndex + 1, numQuestions)
    }
}

