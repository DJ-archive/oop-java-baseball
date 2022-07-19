# 목표
1. 객체 지향적 설계를 고려한 API 개발
2. 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    * https://naver.github.io/hackday-conventions-java
3. 작업 단위별로 커밋을 작성하고, 커밋 메시지 컨벤션을 참고한다.
    * https://gist.github.com/stephenparish/9941e89d80e2bc58a153
4. 풀 리퀘스트, 코드리뷰를 통해 리팩토링 한다.



# 게임 제한 사항

* 답변은 최대 10번까지만 할 수 있다
* 답변 횟수가 최대치를 넘거나, 정답을 맞추는 경우 게임이 종료된 걸로 한다
* room id가 발급되고, 중복되지 않는 1-9 사이의 세 숫자가 정답으로 저장이 되어있어야 함

* 데이터베이스 구현 기술은 결정되지 않은 상태라 db 없이 구현되어야 함 이후에 데이터베이스 구현 기술이 결정되면 db 연동이 추가되기 쉬워야 함


# 구현 API


게임 시작 (POST, /game/start)
```text
response
{
    "success": true,
    "data": {
        "roomId": 123
    }
}
```

---------------------------------------------------------------

게임 진행 (POST /game/123/answer)
```text
request
{    
    "answer": "345"
}

response(게임 종료가 아닐 시)
{
    "success": true,
    "data": {
        "correct": true, // false
        "remainingCount": 8,
        "strike": 3,
        "ball": 0,
        "out": 0
    }
}

resopnse(게임 종료 시)
{
    "success": false,
    "data": null,
    "error": {
        "code": "CLOSED_GAME",
        "message": ""
    }
}
```

---------------------------------------------------------------

게임 결과 (GET /game/123)
```text
response
{
    "success": true,
    "data": {
        "remainingCount": 8,
        "answerCount": 2
    }
}
```


게임 진행 기록 (GET /game/123/history)
```text
response
{    
    "success": true,
    "data": {
        histories: [
            {
                "answer": "123",
                "result": {
                    "strike": 0,
                    "ball": 0,
                    "out": 3
                }
            },
            {
                "answer": "456",
                "result": {
                    "strike": 0,
                    "ball": 2,
                    "out": 1
                }
            }
        ]
    }
}
```
