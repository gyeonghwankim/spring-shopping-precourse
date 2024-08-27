# spring-shopping-precourse

## 기능 요구 사항
- 상품을 조회한다.(Read)
  - Request
```
GET /api/products HTTP/1.1
```
  - Response
```
성공
HTTP/1.1 200 
Content-Type: application/json

[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
]

실패
HTTP/1.1 400 
Content-Type: application/json
{
    "msg" : "누락된 파라미터가 있습니다."
}

```
- 상품을 추가한다.(Create)
  - Request
```
Post /api/products HTTP/1.1

body
{
    "name": "아이스 카페 아메리카노 T"
}
```
  - Response
```
성공
HTTP/1.1 200
{
    "id": 8146027
}

실패
HTTP/1.1 400
{
    "msg" : "누락된 파라미터가 있습니다."
}

HTTP/1.1 400
{
    "msg" : "이미 존재하는 상품입니다."
}
```
- 상품을 수정한다.(Update)
  - Request
```
PUT /api/products/{name} HTTP/1.1

{
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
  - Response
```
성공
HTTP/1.1 200

{
    "id": 8146027
    "name": "아이스 카페 아메리카노 T"
}

실패
HTTP/1.1 400 
{
    "msg" : "누락된 파라미터가 있습니다."
}
```
- 상품을 삭제한다.(Delete)
  - Request
```
DELETE /api/products/{name} HTTP/1.1
```
  - Response
```
성공
HTTP/1.1 200

실패
HTTP/1.1 400 
{
    "msg" : "조회된 상품이 없습니다."
}
```

# 인수 조건
## 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있다.
### 상품은 최대 15자를 넘는 이름을 가져서는 안 된다.
- [*] 동해물과백두산이마르고닳도록
- [ ] 동해물과 백두산이 마르고 닳도록
```gherkin
Given 상품 이름이 "동해물과 백두산이 마르고 닳도록"일 때
 When 상품을 생성하면
 Then 400 Bad Request를 응답한다
  And "상품의 이름은 15자를 넘길 수 없습니다."라고 응답한다.
```

```gherkin
Given 기존 상품이 존재할 때
  And 상품 이름이 "동해물과 백두산이 마르고 닳도록"일 때
 When 상품을 수정하면
 Then 400 Bad Request를 응답한다
  And "상품의 이름은 15자를 넘길 수 없습니다."라고 응답한다.
```

## 상품은 일부 특수 문자는 허용하지 않는다.
### 허용 특수 문자
- 가능: ( ), [ ], +, -, &, /, _
- 그 외 특수 문자 사용 불가
```gherkin
Given 상품 이름이 “(할인) 아메리카노”일 때
 When 상품을 생성하면
 Then 200 OK를 응답한다
```

```gherkin
Given 상품 이름이 “(할인) 아메리카노”일 때
 When 상품을 수정하면
 Then 200 OK를 응답한다
```

```gherkin
Given 상품 이름이 “!!!아메리카노!!!”일 때
 When 상품을 생성하면
 Then 400 Bad Request를 응답한다
  And "허용하지 않는 특수 문자가 포함되어 있습니다."라고 응답한다
```

```gherkin
Given 상품 이름이 “!!!아메리카노!!!”일 때
 When 상품을 수정하면
 Then 400 Bad Request를 응답한다
  And "허용하지 않는 특수 문자가 포함되어 있습니다."라고 응답한다
```

## 상품은 비속어를 포함하지 않는다
- PurgoMalum API를 활용하여 욕설이 포함되어 있는지 확인한다.
```gherkin
Given 상품 이름이 “비속어”가 포함되지 않았을 때
 When 상품을 생성하면
 Then 200 OK를 응답한다
```

```gherkin
Given 상품 이름이 “비속어”가 포함됐을 때
 When 상품을 생성하면
 Then 400 Bad Request를 응답한다
  And "허용하지 않는 비속어가 포함되어 있습니다."라고 응답한다
```

```gherkin
Given 상품 이름이 “비속어”가 포함되지 않았을 때
 When 상품을 수정하면
 Then 200 OK를 응답한다
```

```gherkin
Given 상품 이름이 “비속어”가 포함됐을 때
 When 상품을 수정하면
 Then 400 Bad Request를 응답한다
  And "허용하지 않는 비속어가 포함되어 있습니다."라고 응답한다
```

## 용어 사전

| 한글명 | 영문명 | 설명  |
| --- | --- | --- |
| 상품 | Product | 이름, 가격, 수량, 이미지 URL을  가지는  요소 |
| 클라이언트 | Client | 쇼핑몰을  사용하는  주체, 구매자와  관리자로  구분된다. |
| 구매자 | Buyer | 상품을  조회하고  위시리스트에  등록할  수  있는  클라이언트 |
| 상품 관리자 | Product manager | 상품을  등록하고  제거할  수  있는  클라이언트 |
| 서버 | Server | 쇼핑몰에  대한  요청을  수신하고  응답하는  주체 |
| JSON | JSON | 상품 정보를 반환할 때 사용하는 문법. Key, Value 형태를 가지는 문자 기반의 표준 포맷 |
| 데이터베이스 | Database | 회원 정보와 상품 정보를 다루는 저장소. 쇼핑몰에서는 H2 Database라는  관계형  데이터베이스를  사용한다.|
| 비속어 | Profanity | 사회적으로  부적절하거나  불쾌감을  줄  수  있는  언어 |
| PurgoMalum API | PurgoMalum API | 쇼핑몰에서  비속어  검증을  위해서  사용하는 API |

## 모델링
<img width="310" alt="회원가입" src="https://github.com/user-attachments/assets/05f8adef-d629-448c-9934-6fe31e21f1d1">
<img width="490" alt="상품 등록" src="https://github.com/user-attachments/assets/cdc02c0b-8904-42ec-9f16-bd1925796aa3">




