package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Hamcrest matchers for making assertions on a {@link SsmlOutputSpeech}
 */
public class SsmlOutputSpeechMatcher {

	private SsmlOutputSpeechMatcher() {

	}

	/**
	 * Returns a {@link SsmlOutputSpeechSsmlMatcher} to assert the ssml content of the {@link SsmlOutputSpeech}
	 *
	 * @param expectedSpeechSsml the ssml that the {@link SsmlOutputSpeech} is expected to have
	 * @return the SsmlOutputSpeechSsmlMatcher
	 */
	public static SsmlOutputSpeechSsmlMatcher hasSsml(String expectedSpeechSsml) {
		return new SsmlOutputSpeechSsmlMatcher(expectedSpeechSsml);
	}

	/**
	 * Returns a {@link SsmlOutputSpeechPatternMatcher} to assert that ssml content of the {@link SsmlOutputSpeech}
	 *
	 * @param expectedSpeechSsmlPattern the pattern that the {@link SsmlOutputSpeech}'s ssml is expected to match
	 * @return the SsmlOutputSpeechPatternMatcher
	 */
	public static SsmlOutputSpeechPatternMatcher hasSsml(Pattern expectedSpeechSsmlPattern) {
		return new SsmlOutputSpeechPatternMatcher(expectedSpeechSsmlPattern);
	}

	/**
	 * Hamcrest matcher for asserting {@link SsmlOutputSpeech} has the specified speech ssml
	 */
	static class SsmlOutputSpeechSsmlMatcher extends TypeSafeDiagnosingMatcher<SsmlOutputSpeech> {

		private final String expectedSpeechSsml;

		private SsmlOutputSpeechSsmlMatcher(String expectedSpeechSsml) {
			this.expectedSpeechSsml = expectedSpeechSsml;
		}

		@Override
		protected boolean matchesSafely(SsmlOutputSpeech ssmlOutputSpeech, Description description) {
			String actualSpeechSsml = ssmlOutputSpeech.getSsml();

			if (actualSpeechSsml == null && expectedSpeechSsml == null) {
				return true;
			}

			if (actualSpeechSsml == null && expectedSpeechSsml != null || !actualSpeechSsml.equals(expectedSpeechSsml)) {
				description.appendText(format("was <%s>", actualSpeechSsml));
				return false;
			}

			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(format("Expected SsmlOutputSpeech to have ssml of <%s>", expectedSpeechSsml));
		}

	}

	/**
	 * Hamcrest matcher for asserting the {@link SsmlOutputSpeech} ssml matches the specified pattern
	 */
	static class SsmlOutputSpeechPatternMatcher extends TypeSafeDiagnosingMatcher<SsmlOutputSpeech> {

		private final Pattern expectedSpeechSsmlPattern;

		private SsmlOutputSpeechPatternMatcher(Pattern expectedSpeechSsmlPattern) {
			this.expectedSpeechSsmlPattern = expectedSpeechSsmlPattern;
		}

		@Override
		protected boolean matchesSafely(SsmlOutputSpeech ssmlOutputSpeech, Description description) {
			String actualSpeechSsml = ssmlOutputSpeech.getSsml();

			if (actualSpeechSsml == null && expectedSpeechSsmlPattern == null) {
				return true;
			}

			if (expectedSpeechSsmlPattern == null) {
				description.appendText(format("was <%s>", actualSpeechSsml));
				return false;
			}

			Matcher matcher = expectedSpeechSsmlPattern.matcher(actualSpeechSsml != null ? actualSpeechSsml : "");
			if (actualSpeechSsml == null || !matcher.find()) {
				description.appendText(format("was <%s>", actualSpeechSsml));
				return false;
			}

			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(format("Expected SsmlOutputSpeech to match ssml pattern <%s>", expectedSpeechSsmlPattern != null ? expectedSpeechSsmlPattern.pattern() : null));
		}
	}
}
