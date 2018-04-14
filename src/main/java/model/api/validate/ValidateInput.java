package model.api.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Explanation:
 *
 * ^ # start-of-string (?=.*[0-9]) # a digit must occur at least once
 * (?=.*[a-z]) # a lower case letter must occur at least once (?=.*[A-Z]) # an
 * upper case letter must occur at least once (?=\S+$) # no whitespace allowed
 * in the entire string .{8,20} # anything, at least 8 to 20 places though $ #
 * end-of-string
 */
public class ValidateInput {

    private static final String CARDHOLDER_PATTERN = "^((?:[A-Z]+ ?){1,3})$";

    private static final String USERNAME_PATTERN = "^[A-Za-z0-9_]{6,40}$";

    private static final String MAIL_PATTERN = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

    private static final String NAME_PATTERN = "^(?!\\s*$)[-a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ:\\s]{1,}$";

    private static final String ADDRESS_PATTERN = "^(?!\\s*$)[-a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ:/,-.\\s]{1,}$";

    private static final String PHONE_PATTERN = "^(01[2689]|09)[0-9]{8}$";

    private static final String MASTERCARD_VERIFICATION = "^((5\\d{3})|(5[5]\\d{2})|(6011)|(34\\d{1})|(37\\d{1}))-?\\s?\\d{4}-?\\s?\\d{4}-?\\s?\\d{4}|3[4,7][\\d\\s-]{15}$";

    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private static final String VISA_VERIFICATION = "^((4\\d{3})|(4[4]\\d{2})|(6011)|(34\\d{1})|(37\\d{1}))-?\\s?\\d{4}-?\\s?\\d{4}-?\\s?\\d{4}|3[4,7][\\d\\s-]{15}$\n";

    private static final String VISAMASTER_VERIFICATION = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$";

    private static final String PASSWORD_VERIFICATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,30}$";

    private static final String CVV_VERIFICATION = "^[0-9]{3,4}$";

    /**
     * Check if user enter valid password
     *
     * @param password String, the password that user entered
     * @return true if valid password, false otherwise.
     */
    public boolean check_password(String password) {
        return Pattern.matches(PASSWORD_VERIFICATION, password);
    }

    /**
     * @param username String ,the username that user entered
     * @return true if valid username ,false if different
     */
    public boolean check_username(String username) {
        return Pattern.matches(USERNAME_PATTERN, username);
    }

    /**
     * @param firstname String , the first name of user allow Unicode
     * @return true if valid ,false if different
     */
    public boolean check_name(String firstname) {
        return Pattern.matches(NAME_PATTERN, firstname);
    }

    /**
     * @param mail String , the e-mail address of user not allow whitespace
     * @return true if mail valid , false if different
     */
    public boolean check_mail(String mail) {
        return Pattern.matches(MAIL_PATTERN, mail);
    }

    /**
     * @param phone Number, phone number of user allow Vietnamese phone number
     * @return true if phone number valid , false if different
     */
    public boolean check_phone(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }

    /**
     * @param address String , user address allow Unicode
     * @return true if phone valid , false if different
     */
    public boolean check_address(String address) {
        return Pattern.matches(ADDRESS_PATTERN, address);
    }

    /**
     * @param cardnumber Number , credit number of user
     * @return true if card valid ,false if card is not available
     */
    public boolean check_visa(String cardnumber) {
        return Pattern.matches(VISA_VERIFICATION, cardnumber);

    }

    /**
     * @param cardnumber Number , credit number of user
     * @return true if card valid , false if card if not available
     */
    public boolean check_mastercard(String cardnumber) {
        return Pattern.matches(MASTERCARD_VERIFICATION, cardnumber);

    }

    /**
     * @param cardnumber Number , credit number of user
     * @return true if card valid , false if card if not available
     */
    public boolean check_visamastercard(String cardnumber) {
        return Pattern.matches(VISAMASTER_VERIFICATION, cardnumber);

    }

    /**
     * @param name String, Card holder name
     * @return true if all uppercase allow space ,false if have tab and special
     * character lowercase
     */
    public boolean check_cardholder(String name) {
        return Pattern.matches(CARDHOLDER_PATTERN, name);

    }

    /**
     * @param number
     * @return
     */
    public boolean check_cvv(String number) {

        return Pattern.matches(CVV_VERIFICATION, number);

    }

    /**
     *Validate date format with regular expression, only accept dd/mm/yyyy 
     * @param date
     * @return
     */
    public boolean check_date(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(date);

        if (matcher.matches()) {

            matcher.reset();

            if (matcher.find()) {

                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31")
                        && (month.equals("4") || month.equals("6") || month.equals("9")
                        || month.equals("11") || month.equals("04") || month.equals("06")
                        || month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        return !(day.equals("30") || day.equals("31"));
                    } else {
                        return !(day.equals("29") || day.equals("30") || day.equals("31"));
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
