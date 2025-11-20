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
        const idsResponse = await fetch(`https://partnership-demographic-wiley-closed.trycloudflare.com/api/recommendation`, {
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
            throw new Error("요청 실패");
        }
        const idsJson = await idsResponse.json();

        console.log("idsJson.totalDiaries:", idsJson.totalDiaries);
        console.log("배열인가?", Array.isArray(idsJson.totalDiaries));

        const diaryResponse = await fetch(`http://crewstation.store/api/recommendDiary`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(idsJson.totalDiaries)


        });
        if (!diaryResponse.ok) {
            throw new Error("요청 실패");
        }

        return await diaryResponse.json();
    };


    return { getUserRecommendData : getUserRecommendData, sendMyInfoDatas: sendMyInfoDatas};

})();







