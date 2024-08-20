package shopping.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.domain.ProductMsgResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    @DisplayName("비속어를 사용한 경우 에러 메세지를 응답한다")
    void productNameLengthValidator_errorMsg() {
        //given
        String productName = "bint";
        String expectedMsg = "허용하지 않는 비속어가 포함되어 있습니다.";

        //when
        ProductMsgResponse response = Validator.productNameBadWord(productName);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getMsg()).isEqualTo(expectedMsg);

    }

    @Test
    @DisplayName("비속어를 사용하지 않은 경우 Null을 리턴한다.")
    void productNameLengthValidator_null() {
        //given
        String productName = "bin";

        //when
        ProductMsgResponse response = Validator.productNameBadWord(productName);

        //then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName("올바른 이름이 들어간 경우")
    void productNameSuccessRegexValidator() {
        String prdName = "()아메리카노";

        ProductMsgResponse response = Validator.productNameRegularExpression(prdName);

        String actual = response.getMsg();

        assertThat(actual).isEqualTo("200 OK");
    }

    @Test
    @DisplayName("잘못된 특수문자가 들어간 경우")
    void productNameFailRegexValidator() {
        String prdName = "!!!아메리카노!!!";

        ProductMsgResponse response = Validator.productNameRegularExpression(prdName);

        String actual = response.getMsg();

        assertThat(actual).isEqualTo("허용하지 않는 특수 문자가 포함되어 있습니다.");
    }
}