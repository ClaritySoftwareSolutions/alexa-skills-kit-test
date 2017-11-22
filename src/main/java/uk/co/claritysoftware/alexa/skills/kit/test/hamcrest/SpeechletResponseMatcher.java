package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Hamcrest matchers for making assertions on a {@link SpeechletResponse}
 */
public class SpeechletResponseMatcher {

	private SpeechletResponseMatcher() {

	}

	/**
	 * Returns a {@link SpeechletResponseTypeMatcher} to assert that the {@link SpeechletResponse} is a tell response
	 *
	 * @return the SpeechletRepsonseTypeMatcher
	 */
	public static SpeechletResponseTypeMatcher isATellResponse() {
		return new SpeechletResponseTypeMatcher(true);
	}

	/**
	 * Returns a {@link SpeechletResponseTypeMatcher} to assert that the {@link SpeechletResponse} is an ask response
	 *
	 * @return the SpeechletResponseTypeMatcher
	 */
	public static SpeechletResponseTypeMatcher isAnAskResponse() {
		return new SpeechletResponseTypeMatcher(false);
	}

	/**
	 * Returns a {@link SpeechletResponsePlainTextOutputTextMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 * within the {@link SpeechletResponse}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call
	 * {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher#hasText(String)} with it.</p>
	 *
	 * @param expectedSpeechText the text that the {@link PlainTextOutputSpeech} is expected to have
	 * @return the PlainTextOutputSpeechTextMatcher
	 */
	public static SpeechletResponsePlainTextOutputTextMatcher hasPlainTextOutputSpeechWithText(String expectedSpeechText) {
		return new SpeechletResponsePlainTextOutputTextMatcher(expectedSpeechText);
	}

	/**
	 * Returns a {@link SpeechletResponsePlainTextOutputPatternMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 * within the {@link SpeechletResponse}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link PlainTextOutputSpeech} and then call
	 * {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher#hasText(Pattern)} with it.</p>
	 *
	 * @param expectedSpeechTextPattern the pattern that the {@link PlainTextOutputSpeech} is expected to match
	 * @return the PlainTextOutputSpeechPatternMatcher
	 */
	public static SpeechletResponsePlainTextOutputPatternMatcher hasPlainTextOutputSpeechWithText(Pattern expectedSpeechTextPattern) {
		return new SpeechletResponsePlainTextOutputPatternMatcher(expectedSpeechTextPattern);
	}

	/**
	 * Returns a {@link SpeechletResponseSsmlOutputSsmlMatcher} to assert the ssml content of the {@link SsmlOutputSpeech}
	 * within the {@link SpeechletResponse}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link SsmlOutputSpeech} and then call
	 * {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher#hasSsml(String)} with it.</p>
	 *
	 * @param expectedSpeechSsml the ssml that the {@link SsmlOutputSpeech} is expected to have
	 * @return the SpeechletResponseSsmlOutputSsmlMatcher
	 */
	public static SpeechletResponseSsmlOutputSsmlMatcher hasSsmlOutputSpeechWithSsml(String expectedSpeechSsml) {
		return new SpeechletResponseSsmlOutputSsmlMatcher(expectedSpeechSsml);
	}

	/**
	 * Returns a {@link SpeechletResponseSsmlOutputPatternMatcher} to assert the ssml content of the {@link SsmlOutputSpeech}
	 * within the {@link SpeechletResponse}
	 *
	 * <p>This is really just a convenience method meaning that you don't need to the {@link OutputSpeech} from the {@link SpeechletResponse}
	 * and cast it to a {@link SsmlOutputSpeech} and then call
	 * {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher#hasSsml(Pattern)} with it.</p>
	 *
	 * @param expectedSpeechSsmlPattern the pattern that the {@link SsmlOutputSpeech} is expected to match
	 * @return the SpeechletResponseSsmlOutputPatternMatcher
	 */
	public static SpeechletResponseSsmlOutputPatternMatcher hasSsmlOutputSpeechWithSsml(Pattern expectedSpeechSsmlPattern) {
		return new SpeechletResponseSsmlOutputPatternMatcher(expectedSpeechSsmlPattern);
	}

	/**
	 * Hamcrest matcher for asserting {@link SpeechletResponse} is a tell or ask response
	 */
	static class SpeechletResponseTypeMatcher extends TypeSafeDiagnosingMatcher<SpeechletResponse> {

		private final boolean expectedTellResponse;

		private SpeechletResponseTypeMatcher(boolean expectedTellResponse) {
			this.expectedTellResponse = expectedTellResponse;
		}

		@Override
		protected boolean matchesSafely(SpeechletResponse speechletResponse, Description description) {
			if (expectedTellResponse) {
				if (speechletResponse.getReprompt() != null) {
					description.appendText("has a Reprompt. This is likely to be an Ask Response");
					return false;
				}
				if (!speechletResponse.getShouldEndSession()) {
					description.appendText("but ShouldEndSession is false. This is likely to be an Ask Response");
					return false;
				}
				return true;
			} else {
				if (speechletResponse.getReprompt() == null) {
					description.appendText("has no Reprompt. This is likely to be a Tell Response");
					return false;
				}
				if (speechletResponse.getShouldEndSession()) {
					description.appendText("but ShouldEndSession is true. This is likely to be a Tell Response");
					return false;
				}
				return true;
			}

		}

		@Override
		public void describeTo(Description description) {
			if (expectedTellResponse) {
				description.appendText("Expected SpeechletResponse to be a tell response with no Reprompt and ShouldEndSession set to true");
			} else {
				description.appendText("Expected SpeechletResponse to be an ask response with a Reprompt and ShouldEndSession set to false");
			}
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link SpeechletResponse SpeechletResponse's} {@link PlainTextOutputSpeech} text matches the specified text
	 */
	static class SpeechletResponsePlainTextOutputTextMatcher extends TypeSafeDiagnosingMatcher<SpeechletResponse> {

		private final PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher;

		private SpeechletResponsePlainTextOutputTextMatcher(String expectedSpeechText) {
			matcher = PlainTextOutputSpeechMatcher.hasText(expectedSpeechText);
		}

		@Override
		protected boolean matchesSafely(SpeechletResponse speechletResponse, Description description) {
			return matcher.matchesSafely((PlainTextOutputSpeech) speechletResponse.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link SpeechletResponse SpeechletResponse's} {@link PlainTextOutputSpeech} text matches the specified pattern
	 */
	static class SpeechletResponsePlainTextOutputPatternMatcher extends TypeSafeDiagnosingMatcher<SpeechletResponse> {

		private final PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher;

		private SpeechletResponsePlainTextOutputPatternMatcher(Pattern expectedSpeechTextPattern) {
			matcher = PlainTextOutputSpeechMatcher.hasText(expectedSpeechTextPattern);
		}

		@Override
		protected boolean matchesSafely(SpeechletResponse speechletResponse, Description description) {
			return matcher.matchesSafely((PlainTextOutputSpeech) speechletResponse.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link SpeechletResponse SpeechletResponse's} {@link SsmlOutputSpeech} ssml matches the specified ssml
	 */
	static class SpeechletResponseSsmlOutputSsmlMatcher extends TypeSafeDiagnosingMatcher<SpeechletResponse> {

		private final SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher;

		private SpeechletResponseSsmlOutputSsmlMatcher(String expectedSpeechText) {
			matcher = SsmlOutputSpeechMatcher.hasSsml(expectedSpeechText);
		}

		@Override
		protected boolean matchesSafely(SpeechletResponse speechletResponse, Description description) {
			return matcher.matchesSafely((SsmlOutputSpeech) speechletResponse.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

	/**
	 * Hamcrest matcher for asserting the {@link SpeechletResponse SpeechletResponse's} {@link SsmlOutputSpeech} ssml matches the specified pattern
	 */
	static class SpeechletResponseSsmlOutputPatternMatcher extends TypeSafeDiagnosingMatcher<SpeechletResponse> {

		private final SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher;

		private SpeechletResponseSsmlOutputPatternMatcher(Pattern expectedSpeechTextPattern) {
			matcher = SsmlOutputSpeechMatcher.hasSsml(expectedSpeechTextPattern);
		}

		@Override
		protected boolean matchesSafely(SpeechletResponse speechletResponse, Description description) {
			return matcher.matchesSafely((SsmlOutputSpeech) speechletResponse.getOutputSpeech(), description);
		}

		@Override
		public void describeTo(Description description) {
			matcher.describeTo(description);
		}
	}

}
