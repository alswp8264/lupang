//package item.file;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import item.HashMapItemDAO;
//import item.ItemVO;
//import member.Member;
//
//public class TextFileHashMapitemDAO extends HashMapItemDAO implements FileItemDB {
//
//    private final String dataFilename = DATA_FILE + ".txt";
//    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
//
//    @Override
//    public void saveitems() {
//        try (
//            FileWriter fw = new FileWriter(dataFilename);
//            PrintWriter pw = new PrintWriter(fw);
//        ) {
//            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//
//            for (ItemVO item : itemDB.values()) {
//                pw.println(item.getItemId());
//                pw.println(item.getName());
//                pw.println(item.getGame());
//                pw.println(item.getServer());
//                pw.println(item.getPrice());
//                pw.println(item.getQuantity());
//                pw.println(item.getSeller().getId());
//                pw.println(item.getStatus());  // ✅ 문자열로 저장
//                pw.println(sdf.format(item.getRegDate()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void loaditems() {
//        try (
//            FileReader fr = new FileReader(dataFilename);
//            BufferedReader br = new BufferedReader(fr);
//        ) {
//            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//
//            while (br.ready()) {
//                int itemid = Integer.parseInt(br.readLine());
//                String name = br.readLine();
//                String game = br.readLine();
//                String server = br.readLine();
//                int price = Integer.parseInt(br.readLine());
//                int quantity = Integer.parseInt(br.readLine());
//                String sellerId = br.readLine();
//                String statusStr = br.readLine(); // ✅ 문자열 그대로 사용
//                Date regDate = sdf.parse(br.readLine());
//
//                // ✅ 간소화된 Member 객체 생성
//                Member seller = new Member(sellerId, "판매자", 0, 0, "1234", "구매자"); // 역할도 문자열화
//
//                // ✅ ItemVO 생성자에 문자열 상태 전달
//                ItemVO item = new ItemVO(server, name, game, price, seller, statusStr, quantity);
//                item.setItemId(itemid);
//                item.setRegDate(regDate);
//
//                itemDB.put(itemid, item);
//                if (itemSeq <= itemid) itemSeq = itemid + 1;
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("[로딩 실패] " + dataFilename + " 파일이 없습니다.");
//        } catch (IOException | ParseException e) {
//            System.out.println("[파싱 실패] 아이템 로딩 중 오류 발생:");
//            e.printStackTrace();
//        }
//    }
//}
