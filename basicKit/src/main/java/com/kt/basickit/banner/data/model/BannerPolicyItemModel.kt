package com.kt.basickit.banner.data.model

/**
 * 서버 에서 받아온 BannerPolicy Item 의 raw 정보 Model.
 */
public interface BannerPolicyItemModel {
    /**
     * 배너 ID
     */
    public val id: String

    /**
     * 배너의 우선 순위. (높을 수록 먼저 보여짐.)
     */
    public val priority: Int

    /**
     * 배너 Content 의 종류
     * - "I": image
     * - "T": text
     * - "H": html
     */
    public val contentType: String

    /**
     * 배너 내용. [contentType] 에 따라 다른 내용을 지님.
     * - "I": image url. (모두 에게 open 되어 있어야 함.)
     * - "T": text
     * - "H": HTML 전문.
     */
    public val content: String

    /**
     * landing 할 화면 혹은 "web". landing 하지 않을 경우 null or "none".
     */
    public val landingType: String?

    /**
     * [landingType] 이 "web" 일 경우, 이동할 web url.
     */
    public val landingDestination: String?

    /**
     * PopupBanner 를 닫을 때의 옵션.
     * - "close": 닫기. 한번 보여 주고 다시 보여 주지 않음.
     * - "never": 다시 보지 않기.
     * - "week": 일주일 동안 보지 않기.
     * - "today": 오늘은 보지 않기.
     */
    public val closeType: String?

    /**
     * 해당 배너를 보여줄 AppVersion. null 일 경우 모든 앱버전 에서 보여짐.
     */
    public val appVersion: String?

    /**
     * Default Banner 에서 사용 되는 Banner 의 Category.
     *
     * Default Banner 는 category 에 따라 grouping 된 UI 를 제공함.
     */
    public val category: String?

    /**
     * 배너의 종류.
     * - "popup": PopupBanner(BottomSheet UI)
     * - "default": DefaultBanner(category 별 ImageSlider UI)
     *
     * 이외의 모든 String [type] 을 가진 Banner 는 Entity 변환 과정 에서 전부 버려짐.
     */
    public val type: String

    /**
     * extra format.
     */
    public val additionalInfo: Map<String, String>?
}