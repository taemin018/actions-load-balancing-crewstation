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

    const getDiary = async () => {
        const response = await fetch(`/api/recommend`, {
            method: "GET"
        });

        if (!response.ok) {
            throw new Error("다이어리 불러오기 실패");
        }

        const data = await response.json();
        return data;
    }

    const sendMyInfoDatas = async (myInfoDatas, totalDiaries) => {
        const response = await fetch(`https://every-modular-rank-and.trycloudflare.com/api/recommendation`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                myInfoDatas:myInfoDatas,
                totalDiaries:totalDiaries
            })
        });

        if (!response.ok) {
            throw new Error("요청 실패");
        }

        return await response.json();
    };


    return { getUserRecommendData : getUserRecommendData, sendMyInfoDatas: sendMyInfoDatas, getDiary:getDiary};

})();







