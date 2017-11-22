package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.PlainTextOutputSpeechMatcher.hasText;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Unit test class for {@link PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher}
 */
public class PlainTextOutputSpeechTextMatcherTest {

	@Test
	public void shouldMatchesSafely() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		String expectedSpeechText = "some text";

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText(expectedSpeechText);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenNullExpectedTextAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);

		String expectedSpeechText = null;

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText(expectedSpeechText);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNonMatchingText() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		String expectedSpeechText = "some other value";

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText(expectedSpeechText);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some text>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNullExpectedText() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("some text");

		String expectedSpeechText = null;

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText(expectedSpeechText);

		// When
		boolean matches = matcher.matchesSafely(plainTextOutputSpeech, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <some text>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenExpectedTextAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);

		String expectedSpeechText = "some text";

		Description description = new StringDescription();
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText(expectedSpeechText);

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
		PlainTextOutputSpeechMatcher.PlainTextOutputSpeechTextMatcher matcher = hasText("some text");

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected PlainTextOutputSpeech to have text of <some text>"));
	}
}
