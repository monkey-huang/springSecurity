package Jasper.SideProject.SpringSecurity.Config;

import java.io.UnsupportedEncodingException;
import java.util.Date;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SignatureException;

/**
 * 建置token用的(引入jwts)
 * 
 * @author xing
 *
 */
@Component
@SuppressWarnings("deprecation")
public class TokenManager {

	// 有效時間
	private long tokenEcpiration = 24 * 60 * 60 * 1000;

	// 密碼鑰匙(現在有規範要設長一點密碼)
	private String tokenSignKey = "dasdcjioajoivfjsdiovmoigfmbiovgop,ptyl,bnl;t,[pb';wed;]lp[gad;lgkfl;,sd;l,h;"
			+ "l mfklhmnklgm klghnkl;hgl;n,l;fg,nl;mgfklbkgiovkdfopvkopfsda,v;ldfmvkl;yfmnbklgymionmhfpnmglphfm"
			+ ",kbopghkopbnjtyionmkodfhgkonhnghmnklhgfnmklghfmnkl"
			+ "fioasdjkfiojadsiomkomvdfjkonvkjgbjkgfnbnjkdgbngfnbfnkjgfndbjknnnnnnnnnnnnnnnnnnndggbfgbsadasd"
			+ "asdfjioasjdfoijasiogjtiojg9trjbm;,;lds[pvlcfopaldvpokfrkyoitboityiombklgfmbklmdsklmfklmafklmsfklmasdf"
			+ "askdpfmpov,ydtrn,podmnhomdgklnmklsfgnbmsgfikgpoarkwekrptmbh9064iky350kt-weor-234956-342o-6ot-5eklwgpkeopg"
			+ "asldf-4i3o0-lhg-r0ekh-er0it0-yi45-lyhoktrbopk9-h46o05e=-gaf[pdkg-05o4690-4oy-gtgtopvmksopdkg764907-0369";

	// 1 使用jwt根据用户名生成token
	public String createToken(String username) throws InvalidKeyException, UnsupportedEncodingException {
		
//		Key hmacKey = new SecretKeySpec(Base64.getEncoder().encode(username.getBytes()), 
//                SignatureAlgorithm.HS256.getJcaName());

		String token = Jwts.builder().setSubject(username)
				// 設置到期時間
				.setExpiration(new Date(System.currentTimeMillis() + tokenEcpiration))
				
				.signWith(SignatureAlgorithm.HS512, tokenSignKey.getBytes()).compressWith(CompressionCodecs.GZIP).compact();
		return token;
	}

	// 2 根据token字符串得到用户信息
	public String getUserInfoFromToken(String token) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, UnsupportedEncodingException {
		String userinfo = Jwts.parserBuilder().setSigningKey(tokenSignKey.getBytes()).build().parseClaimsJws(token).getBody()
				.getSubject();
		return userinfo;
	}

	// 3 删除token TODO
	public void removeToken(String token) {
	}
}
