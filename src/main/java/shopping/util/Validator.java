package shopping.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.client.RestClient;
import shopping.domain.ProductMsgResponse;

import java.util.regex.Pattern;

public class Validator {
    public static final String MSG_OK = "200 OK";
    public static final String MSG_CONTAIN_PROFANITY = "허용하지 않는 비속어가 포함되어 있습니다.";
    public static final String MSG_NAME_OVER = "상품의 이름은 15자를 넘길 수 없습니다.";
    public static final String MSG_SPECIAL_CHAR = "허용하지 않는 특수 문자가 포함되어 있습니다.";
    public static ProductMsgResponse productNameValidator(String productName) {
        return new ProductMsgResponse(getProductMsg(productName));
    }

    @NotNull
    private static String getProductMsg(String productName) {
        if (!isValidatedNameLength(productName)) return MSG_NAME_OVER;
        if (!isValidatedNameSpecialChar(productName)) return MSG_SPECIAL_CHAR;
        if (!isValidatedNameProfanity(productName)) return MSG_CONTAIN_PROFANITY;
        return MSG_OK;
    }

    public static boolean isValidatedNameProfanity(String productName) {
        RestClient restClient = RestClient.create();
        String baseUrl = "https://www.purgomalum.com/service/containsprofanity?text=";
//        https://www.purgomalum.com/service/containsprofanity?text=this is some test input
        String result = restClient.get()
                .uri(baseUrl + productName)
                .retrieve()
                .body(String.class);

        if(result.equals("true")) {
            return false;
        }
        return true;
    }

    @Nullable
    public static boolean isValidatedNameLength(String productName) {
        if(productName.length() > 15) {
            return false;
        }
        return true;
    }

    public static boolean isValidatedNameSpecialChar(String productName) {
        String regex = "^[\\w\\s()\\[\\]+\\-&/_가-힣]*$";

        if (!Pattern.matches(regex, productName)) {
            return false;
        }
        return true;
    }
}
