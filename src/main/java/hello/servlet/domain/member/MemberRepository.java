package hello.servlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHasMap, AtomicLong 사용 고려
 * */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findId(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // ArrayList 배열 생성하여 return 하는 이유는
        // 원본 객체 즉, store 안의 객체를 보호하기 위한 것.
        // 하지만 store 의 멤버를 직접가져와서 수정하면 할 수 있음..
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }





}
