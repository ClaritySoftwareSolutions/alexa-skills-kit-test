package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Hamcrest matchers for making assertions on a {@link Reprompt}
 */
public class RepromptMatcher {

	private RepromptMatcher() {

	}

	/**
	 * Returns a {@link RepromptPlainTextOutputTextMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 * within the {@link Reprompt}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call
	 * {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher#hasText(String)} with it.</p>
	 *
	 * @param expectedSpeechText the text that the {@link PlainTextOutputSpeech} is expected to have
	 * @return the PlainTextOutputSpeechTextMatcher
	 */
	public static RepromptPlainTextOutputTextMatcher hasPlainTextOutputSpeechWithText(String expectedSpeechText) {
		return new RepromptPlainTextOutputTextMatcher(expectedSpeechText);
	}

	/**
	 * Returns a {@link RepromptPlainTextOutputPatternMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 * within the {@link Reprompt}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call
	 * {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher#hasText(Pattern)} with it.</p>
	 *
	 * @param expectedSpeechTextPattern the pattern that the {@link PlainTextOutputSpeech} is expected to match
	 * @return the PlainTextOutputSpeechPatternMatcher
	 */
	public static RepromptPlainTextOutputPatternMatcher hasPlainTextOutputSpeechWithText(Pattern expectedSpeechTextPattern) {
		return new RepromptPlainTextOutputPatternMatcher(expectedSpeechTextPattern);
	}

	/**
	 * Returns a {@link RepromptSsmlOutputSsmlMatcher} to assert the ssml content of the {@link SsmlOutputSpeech}
	 * within the {@link Reprompt}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link SsmlOutputSpeech} and then call
	 * {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher#hasSsml(String)} with it.</p>
	 *
	 * @param expectedSpeechSsml the ssml that the {@link SsmlOutputSpeech} is expected to have
	 * @return the RepromptSsmlOutputSsmlMatcher
	 */
	public static RepromptSsmlOutputSsmlMatcher hasSsmlOutputSpeechWithSsml(String expectedSpeechSsml) {
		return new RepromptSsmlOutputSsmlMatcher(expectedSpeechSsml);
	}

	/**
	 * Returns a {@link RepromptSsmlOutputPatternMatcher} to assert the ssml content of the {@link SsmlOutputSpeech}
	 * within the {@link Reprompt}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link Reprompt}
	 * and cast it to a {@link SsmlOutputSpeech} and then call
	 * {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher#hasSsml(Pattern)} with it.</p>
	 *
	 * @param expectedSpeechSsmlPattern the pattern that the {@link SsmlOutputSpeech} is expected to match
	 * @return the RepromptSsmlOutputPatternMatcher
	 */
	public static RepromptSsmlOutputPatternMatcher hasSsmlOutputSpeechWithSsml(Pattern expectedSpeechSsmlPattern) {
		return new RepromptSsmlOutputPatternMatcher(expectedSpeechSsmlPattern);
	}

	/**
	 * Hamcrest matcher for asserting the {@link Reprompt Reprompt's} {@link PlainTextOutputSpeech} text matches the specified text
	 */
	static class RepromptPlainTextOutputTextMatcher extends TypeSafeDiagnosingMatcher<Reprompt> {

		private final PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher;

		private RepromptPlainTextOutputTextMatcher(String expectedSpeechText) {
			matcher = PlainTextOutputSpeechMatcher.hasText(expectedSpeechText);
		}

		@Override
		protected boolean matchesSafely(Reprompt reprompt, Description description) {
			return matcher.matchesSafely((PlainTextOutputSpeech) reprompt.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link Reprompt Reprompt's} {@link PlainTextOutputSpeech} text matches the specified pattern
	 */
	static class RepromptPlainTextOutputPatternMatcher extends TypeSafeDiagnosingMatcher<Reprompt> {

		private final PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher;

		private RepromptPlainTextOutputPatternMatcher(Pattern expectedSpeechTextPattern) {
			matcher = PlainTextOutputSpeechMatcher.hasText(expectedSpeechTextPattern);
		}

		@Override
		protected boolean matchesSafely(Reprompt reprompt, Description description) {
			return matcher.matchesSafely((PlainTextOutputSpeech) reprompt.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link Reprompt Reprompt's} {@link SsmlOutputSpeech} ssml matches the specified ssml
	 */
	static class RepromptSsmlOutputSsmlMatcher extends TypeSafeDiagnosingMatcher<Reprompt> {

		private final SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher;

		private RepromptSsmlOutputSsmlMatcher(String expectedSpeechText) {
			matcher = SsmlOutputSpeechMatcher.hasSsml(expectedSpeechText);
		}

		@Override
		protected boolean matchesSafely(Reprompt reprompt, Description description) {
			return matcher.matchesSafely((SsmlOutputSpeech) reprompt.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link Reprompt Reprompt's} {@link SsmlOutputSpeech} ssml matches the specified pattern
	 */
	static class RepromptSsmlOutputPatternMatcher extends TypeSafeDiagnosingMatcher<Reprompt> {

		private final SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher;

		private RepromptSsmlOutputPatternMatcher(Pattern expectedSpeechTextPattern) {
			matcher = SsmlOutputSpeechMatcher.hasSsml(expectedSpeechTextPattern);
		}

		@Override
		protected boolean matchesSafely(Reprompt reprompt, Description description) {
			return matcher.matchesSafely((SsmlOutputSpeech) reprompt.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

}
