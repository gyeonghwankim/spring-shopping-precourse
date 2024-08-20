package shopping.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.ProductMsgResponse;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void productNameLengthValidator() {

    }

    @Test
    @DisplayName("올바른 이름이 들어간 경우")
    void productNameSuccessRegexValidator() {
        String prdName = "()아메리카노";

        ProductMsgResponse response = Validator.productNameRegularExpression(prdName);

        String actual = response.getMsg();

        Assertions.assertThat(actual).isEqualTo("200 OK");
    }

    @Test
    @DisplayName("잘못된 특수문자가 들어간 경우")
    void productNameFailRegexValidator() {
        String prdName = "!!!아메리카노!!!";

        ProductMsgResponse response = Validator.productNameRegularExpression(prdName);

        String actual = response.getMsg();

        Assertions.assertThat(actual).isEqualTo("허용하지 않는 특수 문자가 포함되어 있습니다.");
    }
}