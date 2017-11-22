package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SsmlOutputSpeechMatcher.hasSsml;

import java.util.regex.Pattern;
import org.junit.Test;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Usage examples for {@link SsmlOutputSpeechMatcher}
 */
public class SsmlOutputSpeechMatcherUsageExampleTest {

	private static final SsmlOutputSpeech SSML_OUTPUT_SPEECH;

	static {
		SSML_OUTPUT_SPEECH = new SsmlOutputSpeech();
		SSML_OUTPUT_SPEECH.setSsml("The quick brown fox jumped <break time=\"1s\"/> over the lazy dog");
	}

	@Test
	public void shouldAssertHasSsmlGivenText() {
		String expectedSsml = "The quick brown fox jumped <break time=\"1s\"/> over the lazy dog";

		assertThat(SSML_OUTPUT_SPEECH, hasSsml(expectedSsml));
	}

	@Test
	public void shouldAssertHasSsmlGivenPattern() {
		Pattern expectedPattern = Pattern.compile("^The quick brown (.+) jumped <break time=\"1s\"/> over the lazy (.+)$");

		assertThat(SSML_OUTPUT_SPEECH, hasSsml(expectedPattern));
	}
}
