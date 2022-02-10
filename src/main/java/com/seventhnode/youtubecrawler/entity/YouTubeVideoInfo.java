package com.seventhnode.youtubecrawler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.seventhnode.youtubecrawler.util.Converter;

@Entity(name = "youtube_video_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class YouTubeVideoInfo extends BaseEntity {

	@Column(name = "video_id")
	private String videoId;

	@Column(name = "title")
	private String title;

	@Column(name = "thumbnail_url")
	private String thumbnailUrl;

	@Column(name = "description")
	private String description;

	@Column(name = "published_date")
	private Date publishedDate;

	@Column(name = "definition")
	private String videoDefinition;

	@Column(name = "duration")
	private String videoDuration;

	@Column(name = "caption")
	private String videoCaption;

	@Column(name = "projection")
	private String videoprojection;

	@Column(name = "country_restricted")
	private String countryRestricted;

	@Column(name = "keyword")
	private String keyword;

	@Column(name = "downloaded")
	private boolean downloaded;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
	private YoutubeChannelInfo channelInfo;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "video_stat_id", referencedColumnName = "video_id")
	private YoutubeVideoStatistics videoStatistics;

	@PrePersist
	private void prePersistFunction() {
		description = Converter.removeAllEmojis(description);
		title = Converter.removeAllEmojis(title);
	}
}
