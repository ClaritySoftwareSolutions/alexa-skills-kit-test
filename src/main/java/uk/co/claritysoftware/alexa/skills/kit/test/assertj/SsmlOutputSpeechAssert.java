package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.api.AbstractAssert;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Specialisation of Assertj {@link AbstractAssert} for making assertions on {@link SsmlOutputSpeech} instances
 */
public class SsmlOutputSpeechAssert extends AbstractAssert<SsmlOutputSpeechAssert, SsmlOutputSpeech> {

	private SsmlOutputSpeechAssert(SsmlOutputSpeech outputSpeech) {
		super(outputSpeech, SsmlOutputSpeechAssert.class);
	}

	public static SsmlOutputSpeechAssert assertThat(SsmlOutputSpeech actual) {
		if (actual == null) {
			throw new IllegalArgumentException("Cannot make assertions on null SsmlOutputSpeech");
		}
		return new SsmlOutputSpeechAssert(actual);
	}

	/**
	 * Assert that the {@link SsmlOutputSpeech} has the specified speech ssml
	 *
	 * @param expectedSpeechSsml the expected speech ssml
	 * @return this {@link SsmlOutputSpeechAssert} for further assertion chaining
	 */
	public SsmlOutputSpeechAssert hasSsml(String expectedSpeechSsml) {
		String actualSpeechSsml = this.actual.getSsml();

		if (actualSpeechSsml == null && expectedSpeechSsml == null) {
			return this;
		}

		if (actualSpeechSsml == null && expectedSpeechSsml != null || !actualSpeechSsml.equals(expectedSpeechSsml)) {
			failWithMessage("Expected SsmlOutputSpeech to have ssml of <%s> but was <%s>", expectedSpeechSsml, actualSpeechSsml);
		}

		return this;
	}

	/**
	 * Assert that the {@link SsmlOutputSpeech} text matches the specified pattern
	 *
	 * @param expectedSpeechSsmlPattern the pattern for the expected speech ssml
	 * @return this {@link SsmlOutputSpeechAssert} for further assertion chaining
	 */
	public SsmlOutputSpeechAssert hasSsml(Pattern expectedSpeechSsmlPattern) {
		String actualSpeechSsml = this.actual.getSsml();

		if (actualSpeechSsml == null && expectedSpeechSsmlPattern == null) {
			return this;
		}

		if (expectedSpeechSsmlPattern == null) {
			failWithMessage("Expected SsmlOutputSpeech to match ssml pattern <%s> but was <%s>", expectedSpeechSsmlPattern, actualSpeechSsml);
		}

		Matcher matcher = expectedSpeechSsmlPattern.matcher(actualSpeechSsml != null ? actualSpeechSsml : "");
		if (actualSpeechSsml == null || !matcher.find()) {
			failWithMessage("Expected SsmlOutputSpeech to match ssml pattern <%s> but was <%s>", expectedSpeechSsmlPattern.pattern(), actualSpeechSsml);
		}

		return this;
	}

}
