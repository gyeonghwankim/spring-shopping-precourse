package shopping.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.ProductMsgResponse;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {

    @Test
    @DisplayName("비속어를 사용한 경우 에러 메세지를 응답한다")
    void productNameLengthValidator_errorMsg() {
        //given
        String productName = "bint";
        String expectedMsg = "허용하지 않는 비속어가 포함되어 있습니다.";

        //when
        ProductMsgResponse response = Validator.productNameValidator(productName);

        //then
        assertThat(response.getMsg()).isEqualTo(Validator.MSG_CONTAIN_PROFANITY);

    }

    @Test
    @DisplayName("비속어를 사용하지 않은 경우 OK 메세지를 리턴한다.")
    void productNameLengthValidator_null() {
        //given
        String productName = "bin";

        //when
        ProductMsgResponse response = Validator.productNameValidator(productName);

        //then
        assertThat(response.getMsg()).isEqualTo(Validator.MSG_OK);
    }

    @Test
    @DisplayName("올바른 이름이 들어간 경우")
    void productNameSuccessRegexValidator() {
        String prdName = "()아메리카노";

        ProductMsgResponse response = Validator.productNameValidator(prdName);

        assertThat(response.getMsg()).isEqualTo(Validator.MSG_OK);
    }

    @Test
    @DisplayName("잘못된 특수문자가 들어간 경우")
    void productNameFailRegexValidator() {
        String prdName = "!!!아메리카노!!!";

        ProductMsgResponse response = Validator.productNameValidator(prdName);

        assertThat(response.getMsg()).isEqualTo(Validator.MSG_SPECIAL_CHAR);
    }
}