package com.thoughtworks.bbs.mappers;

import com.thoughtworks.bbs.model.Post;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PostMapperTest extends MapperTestBase {
    PostMapper postMapper;
    Post post;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        postMapper = getSqlSession().getMapper(PostMapper.class);
        post = new Post().setAuthorName("juntao").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
    }

    @Test
    public void shouldInsertANewPost() {
        int before = postMapper.getAll().size();

        postMapper.insert(post);

        assertThat(postMapper.getAll().size(), is(before + 1));
    }

    @Test
    public void shouldFindMainPostByAuthorName() {
        int before = postMapper.findMainPostByAuthorName("longkai").size();

        Post post1 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(11).setLikeTime(0);
        Post post2 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
        Post post3 = new Post().setAuthorName("juner").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(22).setLikeTime(0);
        Post post4 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
        postMapper.insert(post1);
        postMapper.insert(post2);
        postMapper.insert(post3);
        postMapper.insert(post4);

        List<Post> resultList = postMapper.findMainPostByAuthorName("longkai");

        assertThat(resultList.size(), is(before + 2));
    }

    @Test
    public void shouldFindAllPostByMainPost() {
        int before = postMapper.findAllPostByMainPost(3L).size();

        Post post1 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(3L).setLikeTime(0);
        Post post2 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(2L).setLikeTime(0);
        Post post3 = new Post().setAuthorName("juner").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(3L).setLikeTime(0);
        Post post4 = new Post().setAuthorName("longkai").setTitle("I am a post").setContent("content").setCreateTime(new Date())
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(3L).setLikeTime(0);
        postMapper.insert(post1);
        postMapper.insert(post2);
        postMapper.insert(post3);
        postMapper.insert(post4);

        List<Post> resultList = postMapper.findAllPostByMainPost(3L);

        assertThat(resultList.size(), is(before + 3));
    }

    @Test
    public void shouldFindAllPostOrderByTime() {
        List<Post> before = postMapper.findAllPostsOrderByTime();
        Post post1 = new Post().setAuthorName("first").setTitle("I am a post").setContent("content").setCreateTime(new Date(2013,11,21))
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
        Post post2 = new Post().setAuthorName("second").setTitle("I am a post").setContent("content").setCreateTime(new Date(2013,11,22))
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(1L).setLikeTime(0);
        Post post3 = new Post().setAuthorName("third").setTitle("I am a post").setContent("content").setCreateTime(new Date(2013,11,23))
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
        postMapper.insert(post1);
        postMapper.insert(post2);
        postMapper.insert(post3);

        List<Post> expectedResult = new ArrayList<Post>();
        expectedResult.add(post3);
        expectedResult.add(post1);
        expectedResult.addAll(before);

        List<Post> postResult = postMapper.findAllPostsOrderByTime();

        assertThat(postResult.size(), is(expectedResult.size()));
        assertThat(postResult.get(0).getAuthorName(), is("third"));
        assertThat(postResult.get(1).getAuthorName(), is("first"));

    }

    @Ignore
    public void shouldGetPostIDByAuthorAndTime()
    {
        Post post1 = new Post().setAuthorName("first").setTitle("I am a post").setContent("content").setCreateTime(new Date(2013,11,21))
                .setModifyTime(new Date()).setCreatorId(1L).setModifierId(1L).setParentId(0).setLikeTime(0);
        postMapper.insert(post1);

        Long expected = 1L;
        Long result = postMapper.getPostIDByNameAndTime("first", new Date(2013,11,21));
        assertThat(result,is(expected));
    }
}
