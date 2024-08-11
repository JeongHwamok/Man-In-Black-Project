package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.common.model.vo.PageInfo;
import com.kh.image.model.vo.Image;
import com.kh.like.model.vo.Like;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.Product;

public class ProductService {

	// 상품게시판 작성용 작성용 서비스
		public int insertProduct(Product p	, ArrayList<Image> list) {
			
			// 1)
			Connection conn = getConnection();
			
			// 2-1) INSERT PRODUCT 요청 먼저
			int result1 = new ProductDao().insertProduct(conn, p);
			
			// 2-2) INSERT IMAGE 요청
			int result2 = new ProductDao().insertImages(conn, list);
			// 대표이미지 때문에 적어도 1개이상 데이터가 들어오므로 굳이 if문으로 검사 안해도됨
			
			// 3) 트랜잭션처리
			// 	  둘다 성공했을 경우만 commit
			int result = result1 * result2;
			
			if(result > 0) {
				
				commit(conn);
				
			} else {
				
				rollback(conn);
			}
			
			return result;
		}
		
		public int selectListCount() {

			Connection conn = getConnection();
			
			int count = new ProductDao().selectListCount(conn);
			
			close(conn);
			
			return count;
		}

		public ArrayList<Product> selectProductList(PageInfo pi) {
			
			Connection conn = getConnection();
			
			ArrayList<Product> list = new ProductDao().selectProductList(conn, pi);
			
			close(conn);
			
			return list;
		}
		
		
		// 상품 상세조회용 서비스
		public ArrayList<Product> selectProductDetail(int productNo) {
			
			Connection conn = getConnection();
			
			ArrayList<Product> list = new ProductDao().selectProductDetail(conn, productNo);
			
			close(conn);
			
			return list;
		}

		// 키워드 검색된 상품수 가져오는 서비스
		public int selectKeywordListCount(HashMap<String, String> qStrMap) {
			
			Connection conn = getConnection();

			int count = new ProductDao().selectKeywordListCount(conn, qStrMap);
			
			close(conn);
			
			return count;
		}

		public ArrayList<Product> selectKeywordProductList(HashMap<String, String> qStrMap, PageInfo pi) {

			Connection conn = getConnection();
			
			ArrayList<Product> list = new ProductDao().selectKeywordProductList(conn, qStrMap, pi);
			
			close(conn);
			
			return list;
		}
		
		// 한 상품 이미지 목록리스트
		public ArrayList<Image> selectImgList (int productNo) {
			
			Connection conn = getConnection();
			
			ArrayList<Image> list = new ProductDao().selectImgList(conn, productNo);
			
			close(conn);
			
			return list;
			
			
		}
		
		// 상품 수정용 서비스
		public int updateProduct(Product p, ArrayList<Image> newList) {
			
			// 1)
			Connection conn = getConnection();
			
			ArrayList<Image> oldList = new ProductDao().selectImgList(conn, p.getProductNo());
			
			
			// 새로 추가할 이미지에 섬네일 이미지가 없는 경우
			// > 섬네일 이미지 수정 안할 경우
			//   newList에 oldList의 섬네일을 추가하기
			
			
			
			
			
			int result = 1;
			
			result = new ProductDao().updateProduct(conn, p);
			
			if(newList.size() > 0) {
				for(Image img : oldList) {
					result *= new ProductDao().deleteImage(conn, img.getImageNo());
				}
				
				result *= new ProductDao().updateImages(conn, newList, p.getProductNo());
			}
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}
		// 관리자용 상품전체 조회 서비스브
		public ArrayList<Product> adminSelectProductAll(){
			Connection conn = getConnection();
			
			ArrayList<Product> list = new ProductDao().adminSelectProductAll(conn);
			
			close(conn);
			
			return list;
		}
		//관리자용 상품정보 변경 서비스
		public void adminUpdateProduct(Product p) {
			Connection conn = getConnection();
			
			int result = new ProductDao().adminUpdateProduct(conn,p);
			
			if (result >0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
		}
		
		// 상품삭제 서비스
		public int deleteProduct(int productNo) {
			Connection conn = getConnection();
			
			int result = new ProductDao().deleteProduct(conn,productNo);
			
			if (result >0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
		
		// 좋아요 누름 서비스
		public int insertLike(Like l) {

			// 1)
			Connection conn = getConnection();
			
			// 2) 
			int result = new ProductDao().insertLike(conn, l);
			
			// 3)
			if(result > 0) { // 성공 (commit)
				
				commit(conn);
				
			} else { // 실패 (rollback)
				
				rollback(conn);
			}
			
			// 4) 
			close(conn);
			
			// 5)
			return result;
		}
		
		// 좋아요 해제 서비스
		public int deleteLike(Like l) {

			// 1)
			Connection conn = getConnection();
			
			// 2) 
			int result 
				= new ProductDao().deleteLike(conn, l);
			
			// 3)
			if(result > 0) { // 성공 (commit)
				
				commit(conn);
				
			} else { // 실패 (rollback)
				
				rollback(conn);
			}
			
			// 4) 
			close(conn);
			
			// 5)
			return result;
		}
		
		// 좋아요 확인 서비스
		public int likeCheck(Like l) {
			
			// 1)
			Connection conn = getConnection();
			
			// 2)
			int checkResult
				= new ProductDao().likeCheck(conn, l);
			
			// 3)
			// > select 문이므로 패스
			
			// 4)
			close(conn);
			
			// 5)
			return checkResult;
		}
		
		// 좋아요 갯수 세기 서비스
		public int selectLikeCount(int productNo) {
			
			// 1)
			Connection conn = getConnection();
			
			// 2)
			int likeCount
				= new ProductDao().selectLikeCount(conn, productNo);
			
			// 3)
			// > select 문이므로 패스
			
			// 4)
			close(conn);
			
			// 5)
			return likeCount;
		}
		
		// 마이페이지 좋아요 상품조회 서비스 product
		public ArrayList<Product> likeProductSelect(int MemberNo) {
			
			Connection conn = getConnection();
			
			ArrayList<Product> list = new ProductDao().likeProductSelect(conn, MemberNo);
			
			close(conn);
			
			return list;
		}
		
		
		
}













