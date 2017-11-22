package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SsmlOutputSpeechMatcher.hasSsml;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Unit test class for {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher}
 */
public class SsmlOutputSpeechPatternMatcherTest {

	@Test
	public void shouldMatchesSafely() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		Pattern expectedPattern = Pattern.compile("(.+)me <break time=\"[0-9]s\"/> s(.+)");

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenNullExpectedPatternAndNullSsmlInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);

		Pattern expectedPattern = null;

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNonMatchingPattern() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some <break time=\"1s\"/> ssml>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNullExpectedPattern() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		Pattern expectedPattern = null;

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some <break time=\"1s\"/> ssml>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenExpectedPatternAndNullSsmlInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);

		Pattern expectedPattern = Pattern.compile("(.+)ome text$");

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <null>"));
	}

	@Test
	public void shouldDescribeTo() {
		// Given
		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechPatternMatcher matcher = hasSsml(Pattern.compile("(.+)ome text$"));

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected SsmlOutputSpeech to match ssml pattern <(.+)ome text$>"));
	}
}
