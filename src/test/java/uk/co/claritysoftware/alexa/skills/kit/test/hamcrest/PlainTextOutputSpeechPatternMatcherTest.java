package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.PlainTextOutputSpeechMatcher.hasText;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Unit test class for {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher}
 */
public class PlainTextOutputSpeechPatternMatcherTest {

	@Test
	public void shouldMatchesSafely() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		Pattern expectedPattern = Pattern.compile("(.+)ome text$");

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenNullExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);

		Pattern expectedPattern = null;

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNonMatchingPattern() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some text>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNullExpectedPattern() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		Pattern expectedPattern = null;

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some text>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);

		Pattern expectedPattern = Pattern.compile("(.+)ome text$");

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(expectedPattern);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <null>"));
	}

	@Test
	public void shouldDescribeTo() {
		// Given
		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechPatternMatcher matcher = hasText(Pattern.compile("(.+)ome text$"));

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected PlainTextOutputSpeech to match text pattern <(.+)ome text$>"));
	}
}
