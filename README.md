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

