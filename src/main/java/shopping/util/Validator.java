package shopping.util;

import org.jetbrains.annotations.Nullable;
import shopping.domain.ProductMsgResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static ProductMsgResponse productNameValidator(String productName) {
        //길이
        ProductMsgResponse response = productNameLengthValidator(productName);
        if (response != null) return response;

        //특수문자
        response = productNameRegularExpression(productName);
        if (response != null) return response;

        //욕설
        return null;
    }

    @Nullable
    public static ProductMsgResponse productNameLengthValidator(String productName) {
        if(productName.length() > 15) {
            return new ProductMsgResponse("상품의 이름은 15자를 넘길 수 없습니다.");
        }
        return null;
    }

    public static ProductMsgResponse productNameRegularExpression(String productName) {
        String regex = "^[\\w\\s()\\[\\]+\\-&/_가-힣]*$";

        if (!Pattern.matches(regex, productName)) {
            return new ProductMsgResponse("허용하지 않는 특수 문자가 포함되어 있습니다.");
        }
        return new ProductMsgResponse("200 OK");
    }
}
