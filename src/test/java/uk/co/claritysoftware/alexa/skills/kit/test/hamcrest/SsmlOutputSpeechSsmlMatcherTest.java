package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SsmlOutputSpeechMatcher.hasSsml;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Unit test class for {@link SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher}
 */
public class SsmlOutputSpeechSsmlMatcherTest {

	@Test
	public void shouldMatchesSafely() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		String expectedSpeechSsml = "some <break time=\"1s\"/> ssml";

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml(expectedSpeechSsml);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenNullExpectedSsmlAndNullSsmlInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);

		String expectedSpeechSsml = null;

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml(expectedSpeechSsml);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNonMatchingSsml() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		String expectedSpeechSsml = "some other <break time=\"1s\"/> value";

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml(expectedSpeechSsml);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some <break time=\"1s\"/> ssml>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNullExpectedSsml() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("some <break time=\"1s\"/> ssml");

		String expectedSpeechSsml = null;

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml(expectedSpeechSsml);

		// When
		boolean matches = matcher.matchesSafely(ssmlOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some <break time=\"1s\"/> ssml>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenExpectedSsmlAndNullSsmlInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);

		String expectedSpeechSsml = "some <break time=\"1s\"/> ssml";

		Description description = new StringDescription();
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml(expectedSpeechSsml);

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
		SsmlOutputSpeechMatcher.SsmlOutputSpeechSsmlMatcher matcher = hasSsml("some <break time=\"1s\"/> ssml");

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected SsmlOutputSpeech to have ssml of <some <break time=\"1s\"/> ssml>"));
	}
}
