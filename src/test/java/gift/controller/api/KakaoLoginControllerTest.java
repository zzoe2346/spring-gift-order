package gift.controller.api;

import gift.client.KakaoApiClient;
import gift.client.requestBody.KakaoTokenRequestBody;
import gift.dto.response.JwtTokenResponse;
import gift.dto.response.KakaoTokenResponse;
import gift.service.MemberService;
import gift.service.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings
class KakaoLoginControllerTest {

    @Mock
    private KakaoApiClient kakaoApiClient;
    @Mock
    private MemberService memberService;
    @Mock
    private TokenService tokenService;
    @InjectMocks
    private KakaoLoginController controller;

    @Test
    void getJwtToken() {
        //Given
        String code = "test_code";
        KakaoTokenResponse kakaoTokenResponse = mock(KakaoTokenResponse.class);
        String email = "test@example.com";
        Long memberId = 1L;
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse("jwtToken");

        when(kakaoApiClient.getKakaoToken(any(KakaoTokenRequestBody.class))).thenReturn(kakaoTokenResponse);
        when(kakaoApiClient.getMemberEmail(kakaoTokenResponse.accessToken())).thenReturn(email);
        when(memberService.findMemberIdByEmail(email)).thenReturn(memberId);
        when(tokenService.generateJwtToken(memberId)).thenReturn(jwtTokenResponse);

        //When
        ResponseEntity<JwtTokenResponse> responseEntity = controller.getJwtToken(code);

        //Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().token()).isEqualTo("jwtToken");
    }
}