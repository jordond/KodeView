package dev.snipme.kodeview.view.material3

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import calculateFieldPhraseUpdate
import dev.snipme.highlights.Highlights
import generateAnnotatedString
import androidx.compose.material3.LocalTextStyle as LocalTextStyle3
import androidx.compose.material3.TextField as TextField3
import androidx.compose.material3.TextFieldColors as TextFieldColors3
import androidx.compose.material3.TextFieldDefaults as TextFieldDefaults3

@Composable
fun CodeEditText(
    highlights: Highlights,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    translateTabToSpaces: Boolean = true,
    textStyle: TextStyle = LocalTextStyle3.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults3.shape,
    colors: TextFieldColors3 = TextFieldDefaults3.colors()
) {
    val currentText = remember {
        mutableStateOf(
            TextFieldValue()
        )
    }

    TextField3(
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            val fieldUpdate = it.calculateFieldPhraseUpdate(translateTabToSpaces)
            currentText.value = fieldUpdate
            onValueChange(fieldUpdate.text)
        },
        value = TextFieldValue(
            selection = currentText.value.selection,
            composition = currentText.value.composition,
            annotatedString = buildAnnotatedString {
                generateAnnotatedString(highlights)
            },
        ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun CodeEditTextSwiftUi(
    highlights: Highlights,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    translateTabToSpaces: Boolean = true,
    textStyle: TextStyle = LocalTextStyle3.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults3.shape,
    colors: TextFieldColors3 = TextFieldDefaults3.colors()
) {
    val highlightsState = remember {
        mutableStateOf(highlights)
    }

    val currentText = remember {
        mutableStateOf(
            TextFieldValue().copy(
                annotatedString = buildAnnotatedString {
                    generateAnnotatedString(highlightsState.value)
                }
            )
        )
    }

    TextField3(
        modifier = modifier.fillMaxWidth(),
        value = currentText.value,
        onValueChange = {
            val fieldUpdate = it.calculateFieldPhraseUpdate(translateTabToSpaces)
            highlightsState.value =
                highlightsState.value.getBuilder().code(fieldUpdate.text).build()
            onValueChange(fieldUpdate)
            currentText.value =
                fieldUpdate.copy(
                    annotatedString = buildAnnotatedString {
                        generateAnnotatedString(highlightsState.value)
                    }
                )
        },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

