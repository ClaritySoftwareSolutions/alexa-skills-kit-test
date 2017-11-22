package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import java.util.regex.Pattern;
import org.assertj.core.api.AbstractAssert;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Specialisation of Assertj {@link AbstractAssert} for making assertions on {@link SpeechletResponse} instances
 */
public class SpeechletResponseAssert extends AbstractAssert<SpeechletResponseAssert, SpeechletResponse> {

	private SpeechletResponseAssert(SpeechletResponse reprompt) {
		super(reprompt, SpeechletResponseAssert.class);
	}

	public static SpeechletResponseAssert assertThat(SpeechletResponse actual) {
		if (actual == null) {
			throw new IllegalArgumentException("Cannot make assertions on null SpeechletResponse");
		}
		return new SpeechletResponseAssert(actual);
	}

	/**
	 * Assert that the {@link SpeechletResponse} is an Ask Response
	 *
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert isAnAskResponse() {
		if (this.actual.getReprompt() == null) {
			failWithMessage("Was expecting a Reprompt object, but there was none. This is likely to be a Tell Response");
		}
		if (this.actual.getShouldEndSession()) {
			failWithMessage("Was expecting the session to be set to not end, but it was. This is likely to be a Tell Response");
		}
		return this;
	}

	/**
	 * Assert that the {@link SpeechletResponse} is a Tell Response
	 *
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert isATellResponse() {
		if (this.actual.getReprompt() != null) {
			failWithMessage("Was expecting not to have a Reprompt object, but there was one. This is likely to be an Ask Response");
		}
		if (!this.actual.getShouldEndSession()) {
			failWithMessage("Was expecting the session to be set to end, but it wasn't. This is likely to be an Ask Response");
		}
		return this;
	}

	/**
	 * Assert that the {@link SpeechletResponse} has the specified speech text
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call {@link PlainTextOutputSpeechAssert#assertThat(PlainTextOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechText the expected speech text
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert hasPlainTextOutputSpeechWithText(String expectedSpeechText) {
		PlainTextOutputSpeech outputSpeech = (PlainTextOutputSpeech) this.actual.getOutputSpeech();
		PlainTextOutputSpeechAssert.assertThat(outputSpeech).hasText(expectedSpeechText);

		return this;
	}

	/**
	 * Assert that the {@link SpeechletResponse} text matches the specified pattern
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call {@link PlainTextOutputSpeechAssert#assertThat(PlainTextOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechTextPattern the pattern for the expected speech text
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert hasPlainTextOutputSpeechWithText(Pattern expectedSpeechTextPattern) {
		PlainTextOutputSpeech outputSpeech = (PlainTextOutputSpeech) this.actual.getOutputSpeech();
		PlainTextOutputSpeechAssert.assertThat(outputSpeech).hasText(expectedSpeechTextPattern);

		return this;
	}

	/**
	 * Assert that the {@link SpeechletResponse} has the specified speech ssml
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link SsmlOutputSpeech} and then call {@link SsmlOutputSpeechAssert#assertThat(SsmlOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechSsml the expected speech ssml
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert hasSsmlOutputSpeechWithSsml(String expectedSpeechSsml) {
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) this.actual.getOutputSpeech();
		SsmlOutputSpeechAssert.assertThat(outputSpeech).hasSsml(expectedSpeechSsml);

		return this;
	}

	/**
	 * Assert that the {@link SpeechletResponse} has the specified speech ssml
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link SsmlOutputSpeech} and then call {@link SsmlOutputSpeechAssert#assertThat(SsmlOutputSpeech)} with it.</p>
	 *
	 * @param expectedSpeechSsmlPattern the pattern for the expected speech ssml
	 * @return this {@link SpeechletResponseAssert} for further assertion chaining
	 */
	public SpeechletResponseAssert hasSsmlOutputSpeechWithSsml(Pattern expectedSpeechSsmlPattern) {
		SsmlOutputSpeech outputSpeech = (SsmlOutputSpeech) this.actual.getOutputSpeech();
		SsmlOutputSpeechAssert.assertThat(outputSpeech).hasSsml(expectedSpeechSsmlPattern);

		return this;
	}

}
