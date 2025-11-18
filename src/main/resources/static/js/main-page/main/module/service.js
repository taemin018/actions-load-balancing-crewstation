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
    const getUserRecommendData = async (callback) => {
        const response = await fetch(`/api/userdata`, {
            method: "GET"
        });


        if (!response.ok) {
            throw new Error("추천 게시글 불러오기 실패");
        }

        const data = await response.json();

        if (callback) {
            callback(data);
        }
    };

    return { getUserRecommendData : getUserRecommendData};
})();




