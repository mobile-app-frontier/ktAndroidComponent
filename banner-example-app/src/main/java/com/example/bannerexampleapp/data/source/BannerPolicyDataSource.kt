package com.example.bannerexampleapp.data.source

import com.example.bannerexampleapp.data.model.BannerPolicyItemModelImpl
import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.kt.basickit.banner.data.source.BannerPolicyDataSource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

@ViewModelScoped
class BannerPolicyDataSourceImpl @Inject constructor(): DataSource, BannerPolicyDataSource {
    override suspend fun getBannerPolicy(): List<BannerPolicyItemModel> {
        delay(1000) // 1초 동안 기다림

        val jsonData = """
            [
                {
                    "id": "1",
                    "priority": 3,
                    "contentType": "I",
                    "content": "https://fastly.picsum.photos/id/260/1000/400.jpg?hmac=Tp6zYY3RGo1XXQYRf3h022FzoFy4nkJovOx2NPjCLpI",
                    "landingType": "callHistory",
                    "appVersion": "1.0.0",
                    "category": "callHistory",
                    "type": "default"
                },
                {
                    "id": "2",
                    "priority": 3,
                    "contentType": "I",
                    "content": "https://fastly.picsum.photos/id/202/1000/400.jpg?hmac=l7H4uUZP40TdkMKK3u-JV_VSbw6bdtZq1EJ-sKFWzS8",
                    "landingType": "callHistory",
                    "appVersion": "1.0.0",
                    "category": "callHistory",
                    "type": "default"
                },
                {
                    "id": "3",
                    "priority": 12,
                    "contentType": "I",
                    "content": "https://fastly.picsum.photos/id/691/1000/400.jpg?hmac=nDqC5PptWzwdcPg0xl4o1Pj4sDomKjCUaiZlU8YO9N0",
                    "landingType": "callHistory",
                    "appVersion": "1.0.0",
                    "category": "callHistory",
                    "type": "default"
                },
                {
                    "id": "4",
                    "priority": 2,
                    "contentType": "I",
                    "content": "https://fastly.picsum.photos/id/413/1000/400.jpg?hmac=LBOf-2qpQ7W3XDBxnJjdOeM1eZ3n2r9D7Lw_5_G-B2E",
                    "appVersion": "1.0.0",
                    "category": "callHistory",
                    "type": "default"
                },
                {
                    "id": "51",
                    "priority": 4,
                    "contentType": "T",
                    "content": "공지사항 배너 테스트",
                    "appVersion": "1.0.0",
                    "landingType": "contact",
                    "closeType": "never",
                    "type": "popup"
                },
                {
                    "id": "16",
                    "priority": 4,
                    "contentType": "T",
                    "content": "테스트.........",
                    "appVersion": "1.0.0",
                    "closeType": "never",
                    "type": "popup"
                },
                {
                    "id": "17",
                    "priority": 8,
                    "contentType": "H",
                    "content": "<h1>Hello, <strong>World!</strong></h1>",
                    "appVersion": "1.0.0",
                    "landingType": "call",
                    "closeType": "week",
                    "type": "popup"
                },
                {
                    "id": "8",
                    "priority": 20,
                    "contentType": "I",
                    "content": "https://fastly.picsum.photos/id/565/1000/600.jpg?hmac=oJQa8_RLVzpyhJggqcyNnMUelPH8nqYUaqj65ws0p5c",
                    "appVersion": "1.0.0",
                    "landingType": "web",
                    "landingDestination": "https://docs.swift.org/",
                    "closeType": "today",
                    "type": "popup"
                },
                {
                    "id": "9",
                    "priority": 10,
                    "contentType": "H",
                    "content": "<style> p {text-align: left;}</style><p><strong>업데이트가 완료 되었습니다.<br></strong></p><p><strong>정기휴무일 '주차' ➔ '번째'로 변경 안내</strong></p><p>정기휴무일 설정 단위를 좀더 명확하게 개선하였습니다.</p><p>고객님들께서는 휴무일 순서가 변경되진 않았는지 확인 부탁 드립니다.</p><p>자세한 사항은 공지사항을 참고해주세요!</p><p>※ 설정 방법:</p><p>설정 &gt; 정보관리 &gt; 영업시간 및 휴일</p>",
                    "appVersion": "1.0.0",
                    "landingType": "web",
                    "landingDestination": "https://docs.swift.org/",
                    "closeType": "today",
                    "type": "popup"
                }

            ]
        """

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = com.squareup.moshi.Types.newParameterizedType(List::class.java, BannerPolicyItemModelImpl::class.java)
        val adapter: JsonAdapter<List<BannerPolicyItemModel>> =
            moshi.adapter(listType)//<List<BannerPolicyItemModel>>(BannerPolicyItemModelImpl::class.java).nullSafe()
        return adapter.fromJson(jsonData) ?: listOf()
    }

}