package shopping.util;

import org.jetbrains.annotations.Nullable;
import shopping.domain.ProductMsgResponse;

public class Validator {
    public static ProductMsgResponse productNameValidator(String productName) {
        //길이
        ProductMsgResponse response = productNameLengthValidator(productName);
        if (response != null) return response;

        //특수문자
        //욕설
        return null;
    }

    @Nullable
    private static ProductMsgResponse productNameLengthValidator(String productName) {
        if(productName.length() > 15) {
            return new ProductMsgResponse("상품의 이름은 15자를 넘길 수 없습니다.");
        }
        return null;
    }


}
