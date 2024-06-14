package util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import util.validator.ValidationNationalCode;

public class ValidNationalCode implements ConstraintValidator<ValidationNationalCode, String> {
    @Override
    public void initialize(ValidationNationalCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext constraintValidatorContext) {
        if (!nationalCode.matches("^\\d{10}$"))
            return false;

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(nationalCode.charAt(i)) * (10 - i);
        }

        int lastDigit = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));
        int divideRemaining = sum % 11;

        return ((divideRemaining < 2 && lastDigit == divideRemaining) || (divideRemaining >= 2 && (11 - divideRemaining) == lastDigit));
    }
}
