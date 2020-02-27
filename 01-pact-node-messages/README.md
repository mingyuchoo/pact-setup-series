# 01. Pact Node Messages

## References

- https://reflectoring.io/pact-node-messaging/
- https://blog.shanelee.name/2016/07/19/consumer-driven-contract-testing-using-pact/
- https://github.com/pact-foundation/pact-broker-docker

## 테스트 순서

1. 메시지 구조 정의
2. 소비자측 메시지 처리기 구현
3. 소비자측 계약테스트 실행
4. 소비자측 계약테스트 실행 후 나온 계약서를 Pact Brocker에 게시
5. 공급자측 계약 생성기 구현
6. 공급자측 계약테스트 실행
