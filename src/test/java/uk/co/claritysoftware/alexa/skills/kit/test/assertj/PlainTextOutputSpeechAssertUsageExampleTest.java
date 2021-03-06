package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static uk.co.claritysoftware.alexa.skills.kit.test.assertj.PlainTextOutputSpeechAssert.assertThat;

import java.util.regex.Pattern;
import org.junit.Test;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Usage examples for {@link PlainTextOutputSpeechAssert}
 */
public class PlainTextOutputSpeechAssertUsageExampleTest {

	private static final PlainTextOutputSpeech PLAIN_TEXT_OUTPUT_SPEECH;

	static {
		PLAIN_TEXT_OUTPUT_SPEECH = new PlainTextOutputSpeech();
		PLAIN_TEXT_OUTPUT_SPEECH.setText("The quick brown fox jumped over the lazy dog");
	}

	@Test
	public void shouldAssertHasTextGivenText() {
		String expectedText = "The quick brown fox jumped over the lazy dog";

		assertThat(PLAIN_TEXT_OUTPUT_SPEECH).hasText(expectedText);
	}

	@Test
	public void shouldAssertHasTextGivenPattern() {
		Pattern expectedPattern = Pattern.compile("^The quick brown (.+) jumped over the lazy (.+)$");

		assertThat(PLAIN_TEXT_OUTPUT_SPEECH).hasText(expectedPattern);
	}
}
