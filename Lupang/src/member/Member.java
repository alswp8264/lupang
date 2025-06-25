package member;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import item.ItemVO;

public class Member implements Serializable {
    private int memberNo;
    private String id;
    private String password;
    private String username;
    private String mobile;
    private String email;
    private String address;
    private Date regDate;

    private String role; // ✅ enum Role → 문자열 role
    private int trustScore;
    private int point;
    private LocalDate lastNicknameChangeDate;
    private final List<ItemVO> purchasedItems = new ArrayList<>();

    // ✅ 전체 정보 생성자
    public Member(int memberNo, String id, String password, String username, String mobile,
                  String email, String address, Date regDate, String role) {
        this.memberNo = memberNo;
        this.id = id;
        this.password = password;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.regDate = regDate;
        this.role = (role != null && !role.isBlank()) ? role : "구매자";
        this.trustScore = 0;
        this.point = 0;
    }

    // ✅ 필수 정보 생성자
    public Member(String id, String password, String username) {
        this(0, id, password, username, null, null, null, new Date(), "구매자");
    }

    // ✅ 최소 생성자 (신뢰도 포함)
    public Member(String id, String username, int trustScore, int memberNo, String password, String role) {
        this(memberNo, id, password, username, null, null, null, new Date(), role);
        setTrustScore(trustScore);
    }

    // --- Getter / Setter ---
    public int getMemberNo() { return memberNo; }
    public void setMemberNo(int memberNo) { this.memberNo = memberNo; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    public String getRole() { return role; }
    public void setRole(String role) {
        this.role = (role != null && !role.isBlank()) ? role : "구매자";
    }

    public int getTrustScore() { return trustScore; }
    public void setTrustScore(int trustScore) {
        this.trustScore = Math.max(0, Math.min(100, trustScore));
    }

    public int getPoint() { return point; }
    public void setPoint(int point) {
        this.point = Math.max(0, point);
    }

    public LocalDate getLastNicknameChangeDate() { return lastNicknameChangeDate; }
    public void setLastNicknameChangeDate(LocalDate lastNicknameChangeDate) {
        this.lastNicknameChangeDate = lastNicknameChangeDate;
    }

    // --- 구매 목록 ---
    public void addPurchasedItem(ItemVO item) {
        if (item != null) {
            purchasedItems.add(item);
        }
    }

    public List<ItemVO> getPurchasedItems() {
        return Collections.unmodifiableList(purchasedItems);
    }

    // --- 닉네임 === username ---
    public String getNickname() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String m = (mobile != null) ? mobile : "없음";
        String e = (email != null) ? email : "없음";
        String a = (address != null) ? address : "없음";

        return "[" + memberNo + ", " + id + ", 닉네임: " + username + ", 등록일: " + regDate +
               " (" + m + ", " + e + ", " + a + "), 역할: " +
               (role != null ? role : "알 수 없음") + ", 포인트: " + point + "]";
    }

    public void addPoint(int price) {
        this.point += price;
    }
    public static void printTable(List<Member> members) {
        System.out.println("\n🧑‍🤝‍🧑 전체 회원 목록");
        System.out.println("ID         | 닉네임      | 역할");
        System.out.println("-------------------------------");

        for (Member m : members) {
            String roleLabel = (m.getRole() != null) ? m.getRole() : "-";
            System.out.println(m.getId() + " | " + m.getNickname() + " | " + roleLabel);
        }
    }

    public String toTableRow() {
        return String.format("%-10s | %-10s | %-10s", id, username, (role != null ? role : "-"));
    }
}
