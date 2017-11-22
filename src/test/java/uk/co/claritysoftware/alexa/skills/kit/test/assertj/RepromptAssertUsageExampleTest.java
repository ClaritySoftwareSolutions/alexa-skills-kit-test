package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static uk.co.claritysoftware.alexa.skills.kit.test.assertj.RepromptAssert.assertThat;

import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Usage examples for {@link RepromptAssert}
 */
public class RepromptAssertUsageExampleTest {

	private static final Reprompt REPROMPT = new Reprompt();

	@Before
	public void resetAllOutputSpeeches() {
		REPROMPT.setOutputSpeech(null);
	}

	@Test
	public void shouldAssertHasPlainTextOutputSpeechWithTextGivenText() {
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("The quick brown fox jumped over the lazy dog");
		REPROMPT.setOutputSpeech(plainTextOutputSpeech);
		String expectedText = "The quick brown fox jumped over the lazy dog";

		assertThat(REPROMPT).hasPlainTextOutputSpeechWithText(expectedText);
	}

	@Test
	public void shouldAssertHasPlainTextOutputSpeechWithTextGivenPattern() {
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("The quick brown fox jumped over the lazy dog");
		REPROMPT.setOutputSpeech(plainTextOutputSpeech);
		Pattern expectedPattern = Pattern.compile("^The quick brown fox (.+)$");

		assertThat(REPROMPT).hasPlainTextOutputSpeechWithText(expectedPattern);
	}

	@Test
	public void shouldAssertHasSsmlOutputSpeechWithSsmlGivenSsml() {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("The quick brown fox jumped <break time=\"1s\"/> over the lazy dog");
		REPROMPT.setOutputSpeech(ssmlOutputSpeech);
		String expectedSsml = "The quick brown fox jumped <break time=\"1s\"/> over the lazy dog";

		assertThat(REPROMPT).hasSsmlOutputSpeechWithSsml(expectedSsml);
	}

	@Test
	public void shouldAssertHasSsmlOutputSpeechWithSsmlGivenPattern() {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("The quick brown fox jumped <break time=\"1s\"/> over the lazy dog");
		REPROMPT.setOutputSpeech(ssmlOutputSpeech);
		Pattern expectedPattern = Pattern.compile("^The quick brown fox (.+)$");

		assertThat(REPROMPT).hasSsmlOutputSpeechWithSsml(expectedPattern);
	}
}
