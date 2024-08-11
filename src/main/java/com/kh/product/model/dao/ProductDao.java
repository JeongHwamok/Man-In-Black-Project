package com.kh.product.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.image.model.vo.Image;
import com.kh.like.model.vo.Like;
import com.kh.product.model.vo.Product;

public class ProductDao {
	private Properties prop = new Properties();
		
		public ProductDao() {
			
			String fileName = ProductDao.class.getResource("/sql/product/product-mapper.xml").getPath();
			
			try {
						
				prop.loadFromXML(new FileInputStream(fileName));
						
			} catch (IOException e) {
				e.printStackTrace();
			}			
					
		}

	// 사진게시글 작성용 메소드 (INSERT PRODUCT)
			public int insertProduct(Connection conn, Product p) {
				
				int result = 0;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("insertProduct");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, p.getProductName());
					pstmt.setString(2, p.getProductComment());
					pstmt.setInt(3, p.getProductPrice());
					pstmt.setInt(4, p.getProductStock());
					pstmt.setInt(5, Integer.parseInt(p.getSizeNo()));
					pstmt.setInt(6, Integer.parseInt(p.getSubcategoryNo()));

					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}
			
				// 상품 추가용
				public int insertImages (Connection conn, ArrayList<Image> list) {
				
				// INSERT 문 여러번 > 처리된 행의 갯수들 (int)
				
				// 필요한 변수들 먼저 세팅
				int result = 1;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("insertImages");
				try {
					
					for(Image img : list) {
						// 반복문이 돌 때마다 아래의 과정을 거쳐줄 것
						
						
						pstmt = conn.prepareStatement(sql);
							
						pstmt.setString(1, img.getOriginName());
						pstmt.setString(2, img.getChangeName());
						pstmt.setString(3, img.getImagePath());
						pstmt.setInt(4, img.getImageLevel());
						
						result *= pstmt.executeUpdate();
						// result = result * pstmt.excuteUpdate();
						// > 한놈이라도 실패하면 0이나와서 실패처리 가능
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}
			
			
			public int selectListCount(Connection conn) {
				
				int count = 0;
				
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				String sql = prop.getProperty("selectListCount");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						count = rset.getInt("COUNT");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}
				
				return count;
			}

			// 상품 목록 조회 리스트
		public ArrayList<Product> selectProductList(Connection conn, PageInfo pi) {
			ArrayList<Product> list = new ArrayList<Product>();
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectProductList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				// (현재 페이지 - 1) * 페이지당 표시 글 수 + 1 => 표시할 첫번째 글
				int firstProduct = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				pstmt.setInt(1, firstProduct);
				
				// 첫번째 글에서 (페이지당 표시 글 수 - 1) 한 것을 더하면 표시할 마지막 글이 됨. 총 게시글 수와 비교해서 작은 것을 대입
				int lastProduct = firstProduct + pi.getBoardLimit() - 1;
				pstmt.setInt(2, Integer.min(lastProduct, pi.getListCount()));
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Product p = new Product();
					p.setProductNo(rset.getInt("PRODUCT_NO"));
					p.setProductName(rset.getString("PRODUCT_NAME"));
					p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
					p.setProductStock(rset.getInt("PRODUCT_STOCK"));
					p.setSizeNo(rset.getString("SIZE_NAME"));
					p.setCategoryNo(rset.getString("CATEGORY_NAME"));
					p.setSubcategoryNo(rset.getString("SUBCATEGORY_NAME"));
					p.setTitleImg(rset.getString("IMAGE_PATH"));
					
					list.add(p);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			
			return list;
		}
			
			public ArrayList<Product> selectProductDetail(Connection conn, int productNo) {
				ArrayList<Product> list = new ArrayList<Product>();
				
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				String sql = prop.getProperty("selectProductDetail");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, productNo);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						Product p = new Product();
						
						p.setProductNo(rset.getInt("PRODUCT_NO"));
						p.setProductName(rset.getString("PRODUCT_NAME"));
						p.setProductComment(rset.getString("PRODUCT_COMMENT"));
						p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
						p.setProductStock(rset.getInt("PRODUCT_STOCK"));
						p.setSizeNo(rset.getString("SIZE_NAME"));
						p.setCategoryNo(rset.getString("CATEGORY_NAME"));
						p.setSubcategoryNo(rset.getString("SUBCATEGORY_NAME"));
						p.setTitleImg(rset.getString("IMAGE_PATH"));
						p.setImageLevel(rset.getInt("IMAGE_LEVEL"));
						
						list.add(p);
					}
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}
				
				
				return list;
				
				
			}

			public int selectKeywordListCount(Connection conn, HashMap<String, String> qStrMap) {
				
				int count = 0;
				
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				// key를 이용해서 가져온 값이 null이 아닌 경우
				// > 쿼리스트링이 null이 아닌 경우
				//   product-mapper에 value를 entry로 하는 내용 가져와서 sql에 추가
				
				String sql = prop.getProperty("selectKeywordListCount");
				
				if(qStrMap.get("keyword") != null) {
					sql += "AND PRODUCT_NAME LIKE '%" + qStrMap.get("keyword") + "%'";
					sql += "OR PRODUCT_COMMENT LIKE '%" + qStrMap.get("keyword") + "%'";
				}
				
				if(qStrMap.get("category") != null) {
					sql += prop.getProperty("category");
				}
				
				
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					if(qStrMap.get("category") != null) {
						pstmt.setInt(1, Integer.parseInt(qStrMap.get("category")));
					}
					
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						count = rset.getInt("COUNT");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}
				
				return count;
			}

			public ArrayList<Product> selectKeywordProductList(Connection conn, HashMap<String, String> qStrMap,
					PageInfo pi) {
				
				ArrayList<Product> list = new ArrayList<Product>();
				
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				String sql = prop.getProperty("selectKeywordProductList");
				

				
				// key를 이용해서 가져온 값이 null이 아닌 경우
				// > 쿼리스트링이 null이 아닌 경우
				//   product-mapper에 value를 entry로 하는 내용 가져와서 sql에 추가
				
				if(qStrMap.get("keyword") != null) {
					sql += "AND (PRODUCT_NAME LIKE N'%" + qStrMap.get("keyword") + "%'";
					sql += "OR PRODUCT_COMMENT LIKE N'%" + qStrMap.get("keyword") + "%')";
				}
				
				if(qStrMap.get("category") != null) {
					sql += prop.getProperty("category");
				}
				
				// 여기부터 ORDERBY
				if(qStrMap.get("orderby") != null) {
					sql += prop.getProperty(qStrMap.get("orderby"));
				} else {
					sql += prop.getProperty("date");
				}
				
				
				
				sql += prop.getProperty("selectKeywordProductList2");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					int qStrCount = 0;
					
					if(qStrMap.get("category") != null) {
						pstmt.setInt(1, Integer.parseInt(qStrMap.get("category")));
						qStrCount += 1;
					}
					
					// (현재 페이지 - 1) * 페이지당 표시 글 수 + 1 => 표시할 첫번째 글
					int firstProduct = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
					pstmt.setInt(qStrCount + 1, firstProduct);
					
					// 첫번째 글에서 (페이지당 표시 글 수 - 1) 한 것을 더하면 표시할 마지막 글이 됨. 총 게시글 수와 비교해서 작은 것을 대입
					int lastProduct = firstProduct + pi.getBoardLimit() - 1;
					pstmt.setInt(qStrCount + 2, Integer.min(lastProduct, pi.getListCount()));
					
					
					
					rset = pstmt.executeQuery();
					while(rset.next()) {
						Product p = new Product();
						p.setProductNo(rset.getInt("PRODUCT_NO"));
						p.setProductName(rset.getString("PRODUCT_NAME"));
						p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
						p.setProductStock(rset.getInt("PRODUCT_STOCK"));
						p.setSizeNo(rset.getString("SIZE_NAME"));
						p.setCategoryNo(rset.getString("CATEGORY_NAME"));
						p.setSubcategoryNo(rset.getString("SUBCATEGORY_NAME"));
						p.setTitleImg(rset.getString("IMAGE_PATH"));
						
						list.add(p);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}

				
				
				return list;
				
			}
			
			// 한 상품 이미지목록 리스트
			public ArrayList<Image> selectImgList(Connection conn, int productNo) {
				
				ArrayList<Image> list = new ArrayList<> ();
				
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				String sql = prop.getProperty("selectImgList");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, productNo);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						Image i = new Image(rset.getInt("IMAGE_NO"),
											rset.getString("IMAGE_ORIGIN_NAME"),
											rset.getString("IMAGE_CHANGE_NAME"),
											rset.getString("IMAGE_PATH"),
											rset.getInt("IMAGE_LEVEL"),
											rset.getInt("PRODUCT_NO"));
						list.add(i);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}
				
				return list;
			}
			
			// 상품 수정용 메소드 (update PRODUCT)
			public int updateProduct(Connection conn, Product p) {
				
				int result = 0;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("updateProduct");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, p.getProductName());
					pstmt.setString(2, p.getProductComment());
					pstmt.setInt(3, p.getProductPrice());
					pstmt.setInt(4, p.getProductStock());
					pstmt.setInt(5, Integer.parseInt(p.getSizeNo()));
					pstmt.setInt(6, Integer.parseInt(p.getSubcategoryNo()));
					pstmt.setInt(7, p.getProductNo());
		
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}
			
			// 상품수정용 메소드 (update IMAGE) 
			public int updateProduct (Connection conn, ArrayList<Image> list) {
				
				// INSERT 문 여러번 > 처리된 행의 갯수들 (int)
				
				// 필요한 변수들 먼저 세팅
				int result = 1;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("updateImages");
				try {
					
					for(Image img : list) {
						if(img == null ) {
							continue;
						}
						// 반복문이 돌 때마다 아래의 과정을 거쳐줄 것
						
						pstmt = conn.prepareStatement(sql);
							
						pstmt.setString(1, img.getOriginName());
						pstmt.setString(2, img.getChangeName());
						pstmt.setInt(3, img.getImageNo());
						
						result *= pstmt.executeUpdate();
						// result = result * pstmt.excuteUpdate();
						// > 한놈이라도 실패하면 0이나와서 실패처리 가능
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}
			
			// 사진게시글 수정용 (INSERT IMAGE)
			public int updateImages (Connection conn, ArrayList<Image> list, int productNo) {
				
				
				// 필요한 변수들 먼저 세팅
				int result = 1;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("updateImages");
				try {
					
					for(Image img : list) {
						// 반복문이 돌 때마다 아래의 과정을 거쳐줄 것
						pstmt = conn.prepareStatement(sql);
							
						pstmt.setString(1, img.getOriginName());
						pstmt.setString(2, img.getChangeName());
						pstmt.setString(3, img.getImagePath());
						pstmt.setInt(4, img.getImageLevel());
						pstmt.setInt(5, productNo);
						
						result *= pstmt.executeUpdate();
						// result = result * pstmt.excuteUpdate();
						// > 한놈이라도 실패하면 0이나와서 실패처리 가능
					}
				} catch (SQLException e) {
					e.printStackTrace();
					result = 0;
				} finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}

			public int deleteImage(Connection conn, int imageNo) {
				int result = 0;
				
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("deleteImage");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, imageNo);
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
					result = 0;
				} finally {
					close(pstmt);
				}
				return result;
			}				
			//관리자용 상품 전체조회
			public ArrayList<Product> adminSelectProductAll(Connection conn){
				ArrayList<Product> list = new ArrayList<>();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = prop.getProperty("adminSelectProductAll");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						Product p = new Product();
						p.setProductNo(rs.getInt("PRODUCT_NO"));
						p.setProductName(rs.getString("PRODUCT_NAME"));
						p.setProductComment(rs.getString("PRODUCT_COMMENT"));
						p.setProductPrice(rs.getInt("PRODUCT_PRICE"));
						p.setProductStock(rs.getInt("PRODUCT_STOCK"));
						p.setProductDate(rs.getDate("PRODUCT_DATE"));
						p.setProductStatus(rs.getString("PRODUCT_STATUS"));
						p.setSizeNo(rs.getString("SIZE_NO"));
						p.setSubcategoryNo(rs.getString("SUBCATEGORY_NO"));
						
						list.add(p);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(pstmt);
				}
				
				return list;
				
			}
			//관리자용 상품정보 변경
			public int adminUpdateProduct(Connection conn,Product p) {
				int result = 0;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("adminUpdateProduct");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, p.getProductStatus());
					pstmt.setInt(2, p.getProductNo());
					
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}

			// 상품삭제용 메소드
			public int deleteProduct(Connection conn, int ProductNo) {
				
				int result = 0;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("deleteProduct");
				
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, ProductNo);
					
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					
					close(pstmt);
					
				}
				
				
				
				return result;
				
			}
		
		// 좋아요 누름 메소드
		public int insertLike(Connection conn, Like l) {

			// INSERT 문 > 처리된 행의 갯수 (int)
			
			// 필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("insertLike");
			
			try {
				
				// 1)
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, l.getMemberNo());
				pstmt.setInt(2, l.getProductNo());
				
				// 3)
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 4)
				JDBCTemplate.close(pstmt);
			}
			
			// 5)
			return result;
		}
		
		// 좋아요 해제 메소드
		public int deleteLike(Connection conn, Like l) {

			// INSERT 문 > 처리된 행의 갯수 (int)
			
			// 필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("deleteLike");
			
			try {
				
				// 1)
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, l.getMemberNo());
				pstmt.setInt(2, l.getProductNo());
				
				// 3)
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 4)
				JDBCTemplate.close(pstmt);
			}
			
			// 5)
			return result;
		}
		
		// 좋아요 확인 메소드
		public int likeCheck(Connection conn, Like l) {

			// SELECT 문 > ResultSet 객체 (단일행)
			// > int
			
			// 필요한 변수들 먼저 셋팅
			int checkResult = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("likeCheck");
			
			try {
				
				// 1)
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, l.getMemberNo());
				pstmt.setInt(2, l.getProductNo());
				
				// 3)
				rset = pstmt.executeQuery();
				
				// 4)
				if(rset.next()) {
					
					checkResult = rset.getInt("COUNT(*)");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 5)
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// 6)
			return checkResult;
		}
		
		// 좋아요 갯수 세기 메소드
		public int selectLikeCount(Connection conn, int productNo) {

			// SELECT 문 > ResultSet 객체 (단일행)
			// > int
			
			// 필요한 변수들 먼저 셋팅
			int likeCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 쿼리문
			String sql = prop.getProperty("selectLikeCount");
			
			try {
				
				// 1)
				pstmt = conn.prepareStatement(sql);
				
				// 2)
				pstmt.setInt(1, productNo);
				
				// 3)
				rset = pstmt.executeQuery();
				
				// 4)
				if(rset.next()) {
					
					likeCount = rset.getInt("COUNT(*)");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 5)
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// 6)
			return likeCount;
		}
		
		// 마이페이지 좋아요 상품조회 product 메소드
		public ArrayList<Product> likeProductSelect(Connection conn, int MemberNo) {
			
			ArrayList<Product> list = new ArrayList<> ();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("likeSelect");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, MemberNo);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Product p = new Product();
					
					p.setProductNo(rset.getInt("PRODUCT_NO"));
					p.setProductName(rset.getString("PRODUCT_NAME"));
					p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
					p.setSizeNo(rset.getString("SIZE_NAME"));
					p.setCategoryNo(rset.getString("CATEGORY_NAME"));
					p.setSubcategoryNo(rset.getString("SUBCATEGORY_NAME"));
					p.setTitleImg(rset.getString("IMAGE_PATH"));
					
					list.add(p);
				}
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			
			return list;
			
		}
		
		
		
		
		
		
}