package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static uk.co.claritysoftware.alexa.skills.kit.test.assertj.SpeechletResponseAssert.assertThat;

import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Usage examples for {@link SpeechletResponseAssert}
 */
public class SpeechletResponseAssertUsageExampleTest {

	private static final SpeechletResponse TELL_SPEECHLET_RESPONSE;

	private static final SpeechletResponse ASK_SPEECHLET_RESPONSE;

	private static final Reprompt REPROMPT;

	static {
		TELL_SPEECHLET_RESPONSE = new SpeechletResponse();
		TELL_SPEECHLET_RESPONSE.setReprompt(null);
		TELL_SPEECHLET_RESPONSE.setShouldEndSession(true);

		REPROMPT = new Reprompt();
		ASK_SPEECHLET_RESPONSE = new SpeechletResponse();
		ASK_SPEECHLET_RESPONSE.setReprompt(REPROMPT);
		ASK_SPEECHLET_RESPONSE.setShouldEndSession(false);
	}

	@Before
	public void resetAllOutputSpeeches() {
		TELL_SPEECHLET_RESPONSE.setOutputSpeech(null);
		REPROMPT.setOutputSpeech(null);
		ASK_SPEECHLET_RESPONSE.setOutputSpeech(null);
	}

	@Test
	public void shouldAssertIsATellResponse() {
		assertThat(TELL_SPEECHLET_RESPONSE).isATellResponse();
	}

	@Test
	public void shouldAssertIsAnAskResponse() {
		assertThat(ASK_SPEECHLET_RESPONSE).isAnAskResponse();
	}

	@Test
	public void shouldAssertHasPlainTextOutputSpeechWithTextGivenText() {
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("The quick brown fox jumped over the lazy dog");
		TELL_SPEECHLET_RESPONSE.setOutputSpeech(plainTextOutputSpeech);
		String expectedText = "The quick brown fox jumped over the lazy dog";

		assertThat(TELL_SPEECHLET_RESPONSE).hasPlainTextOutputSpeechWithText(expectedText);
	}

	@Test
	public void shouldAssertHasPlainTextOutputSpeechWithTextGivenPattern() {
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("The quick brown fox jumped over the lazy dog");
		TELL_SPEECHLET_RESPONSE.setOutputSpeech(plainTextOutputSpeech);
		Pattern expectedPattern = Pattern.compile("^The quick brown fox (.+)$");

		assertThat(TELL_SPEECHLET_RESPONSE).hasPlainTextOutputSpeechWithText(expectedPattern);
	}

	@Test
	public void shouldAssertHasSsmlOutputSpeechWithSsmlGivenSsml() {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("The quick brown fox jumped <break time=\"1s\"/> over the lazy dog");
		TELL_SPEECHLET_RESPONSE.setOutputSpeech(ssmlOutputSpeech);
		String expectedSsml = "The quick brown fox jumped <break time=\"1s\"/> over the lazy dog";

		assertThat(TELL_SPEECHLET_RESPONSE).hasSsmlOutputSpeechWithSsml(expectedSsml);
	}

	@Test
	public void shouldAssertHasSsmlOutputSpeechWithSsmlGivenPattern() {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("The quick brown fox jumped <break time=\"1s\"/> over the lazy dog");
		TELL_SPEECHLET_RESPONSE.setOutputSpeech(ssmlOutputSpeech);
		Pattern expectedPattern = Pattern.compile("^The quick brown fox (.+)$");

		assertThat(TELL_SPEECHLET_RESPONSE).hasSsmlOutputSpeechWithSsml(expectedPattern);
	}

}
