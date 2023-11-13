INSERT INTO product (product_id, name, category, price, stock, sales, description, created_at)
VALUES (UNHEX(REPLACE('d6f9c4bd-8f4e-4c1f-8a2b-eae9a9decf1b', '-', '')), '저탄소 샤인머스캣', 'FRUITS_AND_VEGETABLES', 10900,
        50, 0,
        '상큼함으로 무장한 연둣빛 포도', NOW()),
       (UNHEX(REPLACE('d7aa2d4e-7c14-4e22-8a56-d3d19ed4d5ce', '-', '')), '실속 바나나', 'FRUITS_AND_VEGETABLES', 3990, 100,
        0,
        '매일 즐기는 달콤한 포만감', NOW()),
       (UNHEX(REPLACE('f1e4c7c1-6b07-48c1-9103-e9a3c678e9b9', '-', '')), '냉동 블루베리', 'FRUITS_AND_VEGETABLES', 9900, 30,
        0,
        '달콤하게 즐기는 보랏빛 과육', NOW()),
       (UNHEX(REPLACE('a2d2c1e9-6b07-4e88-a4d6-e9b9c678e9b9', '-', '')), '한통 양배추', 'FRUITS_AND_VEGETABLES', 5190, 60, 0,
        '아삭한 잎에 깃든 달콤함', NOW()),
       (UNHEX(REPLACE('c3f1e4d1-5b05-4c21-9111-d4a2c678e9b9', '-', '')), '친환경 당근', 'FRUITS_AND_VEGETABLES', 6190, 40, 0,
        '베타카로틴이 풍부한 주황빛 채소', NOW()),
       (UNHEX(REPLACE('541395a9-3faa-4c82-895d-ac5f3374eb66', '-', '')), '스테비아 토마토', 'FRUITS_AND_VEGETABLES', 8980, 40, 0,
        '설탕 없이도 달달함을 머금은 방울토마토', NOW());

INSERT INTO member (member_id, name, email, address, created_at, updated_at)
VALUES (UNHEX(REPLACE('c2516e36-6ef4-4a8b-be0c-c10c51857dbe', '-', '')), '홍길동', 'hong@test.com', '서울특별시 용산구 한강대로 405',
        NOW(), NULL),
       (UNHEX(REPLACE('78f5d095-4ef8-4652-967d-2d3049fd1a1e', '-', '')), '김철수', 'kim@test.com', '부산광역시 수영구 민락동 700',
        NOW(), NULL),
       (UNHEX(REPLACE('299a8e02-d69a-4cd1-82b3-859aa87f1419', '-', '')), '이영희', 'lee@test.com', '대구광역시 중구 공평로 88',
        NOW(), NULL),
       (UNHEX(REPLACE('2ba8b92d-3c04-4683-a80c-34bcdd70f458', '-', '')), '박지민', 'park@test.com', '인천광역시 남동구 구월동 23',
        NOW(), NULL),
       (UNHEX(REPLACE('432e9771-197c-43ff-8098-79f36767e14d', '-', '')), '최수연', 'choi@test.com', '광주광역시 남구 봉선로 1',
        NOW(), NULL);

