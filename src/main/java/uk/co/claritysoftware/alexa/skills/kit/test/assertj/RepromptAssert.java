package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import java.util.regex.Pattern;
import org.assertj.core.api.AbstractAssert;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Specialisation of Assertj {@link AbstractAssert} for making assertions on {@link Reprompt} instances
 */
public class RepromptAssert extends AbstractAssert<RepromptAssert, Reprompt> {

	private RepromptAssert(Reprompt reprompt) {
		super(reprompt, RepromptAssert.class);
	}

	public static RepromptAssert assertThat(Reprompt actual) {
		if (actual == null) {
			throw new IllegalArgumentException("Cannot make assertions on null Reprompt");
		}
		return new RepromptAssert(actual);
	}

	/**
	 * Assert that the {@link Reprompt} has the specified speech text
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call {@link PlainTextOutputSpeechAssert#assertThat(PlainTextOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechText the expected speech text
	 * @return this {@link RepromptAssert} for further assertion chaining
	 */
	public RepromptAssert hasPlainTextOutputSpeechWithText(String expectedSpeechText) {
		PlainTextOutputSpeech outputSpeech = (PlainTextOutputSpeech) this.actual.getOutputSpeech();
		PlainTextOutputSpeechAssert.assertThat(outputSpeech).hasText(expectedSpeechText);

		return this;
	}

	/**
	 * Assert that the {@link Reprompt} has the specified pattern
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call {@link PlainTextOutputSpeechAssert#assertThat(PlainTextOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechTextPattern the pattern for the expected speech text
	 * @return this {@link RepromptAssert} for further assertion chaining
	 */
	public RepromptAssert hasPlainTextOutputSpeechWithText(Pattern expectedSpeechTextPattern) {
		PlainTextOutputSpeech outputSpeech = (PlainTextOutputSpeech) this.actual.getOutputSpeech();
		PlainTextOutputSpeechAssert.assertThat(outputSpeech).hasText(expectedSpeechTextPattern);

		return this;
	}

	/**
	 * Assert that the {@link Reprompt} has the specified speech ssml
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link SsmlOutputSpeech} and then call {@link SsmlOutputSpeechAssert#assertThat(SsmlOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechSsml the expected speech ssml
	 * @return this {@link RepromptAssert} for further assertion chaining
	 */
	public RepromptAssert hasSsmlOutputSpeechWithSsml(String expectedSpeechSsml) {
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) this.actual.getOutputSpeech();
		SsmlOutputSpeechAssert.assertThat(outputSpeech).hasSsml(expectedSpeechSsml);

		return this;
	}

	/**
	 * Assert that the {@link Reprompt} has the specified pattern
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link SsmlOutputSpeech} and then call {@link SsmlOutputSpeechAssert#assertThat(SsmlOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechSsmlPattern the pattern for the expected speech ssml
	 * @return this {@link RepromptAssert} for further assertion chaining
	 */
	public RepromptAssert hasSsmlOutputSpeechWithSsml(Pattern expectedSpeechSsmlPattern) {
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) this.actual.getOutputSpeech();
		SsmlOutputSpeechAssert.assertThat(outputSpeech).hasSsml(expectedSpeechSsmlPattern);

		return this;
	}

}
