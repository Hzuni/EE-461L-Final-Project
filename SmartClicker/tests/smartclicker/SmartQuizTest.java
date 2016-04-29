package smartclicker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import smartclicker.SmartQuiz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SmartQuizTest {
	SmartQuiz myQuiz = new SmartQuiz();

	@Before
	public void setUp() throws Exception {
		ArrayList<SmartQuiz> quizes = new ArrayList<SmartQuiz>();
		ArrayList<SmartQuestion> questions = new ArrayList<SmartQuestion>();
		ArrayList<String> answers = new ArrayList<String>();
		SmartQuestion myQuestion = new SmartQuestion();
		myQuestion.setQuestion("TestQuestion");
		answers.add("TestAnswer1");
		answers.add("TestAnswer2");
		answers.add("TestAnswer3");
		answers.add("TestAnswer4");
		myQuestion.setAnswers(answers);
		myQuestion.setCorrect(1);
		questions.add(myQuestion);
		myQuiz.setTitle("TestTitle");
		myQuiz.setQuestions(questions);
		myQuiz.setQuizID("1234");
		quizes.add(myQuiz);
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testGetQuizID() {
		assertEquals(myQuiz.getQuizID(), "1234");
	}

	@Test
	public void testQuestions() {
		ArrayList<SmartQuestion> questions = myQuiz.getQuestions();
		SmartQuestion myQuestion = questions.get(0);
		ArrayList<String> answers = myQuestion.getAnswers();
		String answer1 = answers.get(0);
		String answer2 = answers.get(1);
		String answer3 = answers.get(2);
		String answer4 = answers.get(3);
		String question = myQuestion.getQuestion();
		assertEquals(answer1, "TestAnswer1");
		assertEquals(answer2, "TestAnswer2");
		assertEquals(answer3, "TestAnswer3");
		assertEquals(answer4, "TestAnswer4");
		assertEquals(question, "TestQuestion");
	}

	@Test
	public void testGetTitle() {
		assertEquals(myQuiz.getTitle(), "TestTitle");
	}

}
