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
    "msg" : "누락된 파라미터가 있습니다."
}
```