const likeService = (() => {
    const addLike = async (postId) => {
        const response = await fetch(`/api/likes/${postId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ postId }),
        });
        if (!response.ok) throw new Error("좋아요 추가 실패");
        return await response.text();
    };

    const removeLike = async (postId) => {
        const response = await fetch(`/api/likes/${postId}`, {
            method: "DELETE",
        });
        if (!response.ok) throw new Error("좋아요 취소 실패");
        return await response.text();
    };

    return { addLike : addLike, removeLike : removeLike };
})();


const recommendService = (() => {
    const getUserRecommendData = async () => {
        const response = await fetch(`/api/userdata`, {
            method: "GET"
        });

        if (!response.ok) {
            throw new Error("추천 데이터 불러오기 실패");
        }

        const data = await response.json();
        return data;
    };


    const sendMyInfoDatas = async (myInfoDatas, totalDiaries) => {
        const idsResponse = await fetch(`https://socks-willow-variety-juan.trycloudflare.com/api/recommendation`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                myInfoDatas:myInfoDatas,
                totalDiaries:totalDiaries
            })
        });

        if (!idsResponse.ok) {
            throw new Error("추천 요청 실패");
        }

        const ids = await idsResponse.json();
        console.log("ids:", ids);
        console.log(Array.isArray(ids.recommendPost));



        const diaryResponse = await fetch(`/api/recommendDiary`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(ids.recommendPost)

        });


        if (!diaryResponse.ok) {
            throw new Error("다이어리 요청 실패");
        }

        const diaryData = await diaryResponse.json();
        console.log("diaryData:", diaryData);

        return diaryData;

    };

    const getTotalDiariesData = async () => {
        const response = await fetch(`/api/totalDiaries`, {
            method: "GET"
        });
        if (!response.ok) throw new Error("전체 게시글 불러오기 실패");

        const totalDiaryData = await response.json();
        console.log("전체 다이어리: ", totalDiaryData);

        return totalDiaryData
    };



    return { getUserRecommendData : getUserRecommendData, sendMyInfoDatas: sendMyInfoDatas, getTotalDiariesData:getTotalDiariesData};

})();







