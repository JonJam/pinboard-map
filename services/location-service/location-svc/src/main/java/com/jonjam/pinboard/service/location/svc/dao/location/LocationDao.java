package com.jonjam.pinboard.service.location.svc.dao.location;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.jonjam.pinboard.service.location.svc.dao.location.model.Location;
import org.jdbi.v3.core.Jdbi;

public class LocationDao {

    private final Jdbi jdbi;

    @Inject
    public LocationDao(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Location getByCodeOrThrow(final long locationCode) {
        jdbi.withHandle(h -> {

        });

//        return jdbcHelper.query(
//            "SELECT campaign_id, campaign_code, expiry_after_value, expiry_after_unit_id, applicable_from, applicable_through, applicable_time_zone_id, campaign_status_id, user_allocation_limit",
//            "FROM campaign",
//            "WHERE campaign_id = @id")
//                         .withParameter("id", campaignId)
//                         .mapResultsWith(this::campaignMapper)
//                         .toOptional()
//                         .orElseThrow(() -> new IllegalStateException("Campaign not found"));
    }

//    public long upsert(final UpsertCampaignDaoRequest upsertCampaignDaoRequest) {
//        return jdbcHelper.insertInto("campaign")
//                         .setValue("campaign_code", upsertCampaignDaoRequest.getCampaignCode())
//                         .setValue("expiry_after_value", upsertCampaignDaoRequest.getExpiryAfterValue())
//                         .setValue("expiry_after_unit_id", upsertCampaignDaoRequest.getExpiryAfterUnit().map(PromotionCodeExpiryAfterUnit::getId))
//                         .setValue("applicable_from", upsertCampaignDaoRequest.getApplicableFrom())
//                         .setValue("applicable_through", upsertCampaignDaoRequest.getApplicableThrough())
//                         .setValue("applicable_time_zone_id", upsertCampaignDaoRequest.getApplicableTimeZone().map(CampaignTimeZone::getId))
//                         .setValue("campaign_status_id", upsertCampaignDaoRequest.getCampaignStatus().getId())
//                         .setValue("user_allocation_limit", upsertCampaignDaoRequest.getUserAllocationLimit())
//                         .setToNow("updated_at")
//                         .setValue("updated_by", callerIdentity.getDisplayName())
//                         .onConflict("campaign_code")
//                         .doUpdateUsingInsertValues()
//                         .executeAndReturnGeneratedKey();
//    }

    private Location locationMapper(final QueryResult queryResult) {
//        final Optional<PromotionCodeExpiryAfterUnit> expiryAfterUnit = queryResult.getOptionalInt("expiry_after_unit_id")
//                                                                                  .flatMap(PromotionCodeExpiryAfterUnit::fromDatabaseRepresentation);
//
//        final CampaignCode campaignCode = CampaignCode.valueOf(queryResult.getString("campaign_code"));
//        final CampaignStatus campaignStatus = CampaignStatus.fromDatabaseRepresentation(queryResult.getInt("campaign_status_id"))
//                                                            .orElseThrow(() -> ILLEGAL_CAMPAIGN_STATUS_EXCEPTION);
//        final CampaignTimeZone applicableTimeZone = CampaignTimeZone.fromDatabaseRepresentation(queryResult.getInt("applicable_time_zone_id"))
//                                                                    .orElseThrow(() -> ILLEGAL_CAMPAIGN_TIME_ZONE_EXCEPTION);
//
//        return new Campaign.Builder()
//            .withCampaignId(queryResult.getLong("campaign_id"))
//            .withCampaignCode(campaignCode)
//            .withExpiryAfterValue(queryResult.getOptionalInt("expiry_after_value"))
//            .withExpiryAfterUnit(expiryAfterUnit)
//            .withApplicableFrom(queryResult.getLocalDateTime("applicable_from"))
//            .withApplicableThrough(queryResult.getOptionalLocalDateTime("applicable_through"))
//            .withApplicableTimeZone(applicableTimeZone)
//            .withCampaignStatus(campaignStatus)
//            .withUserAllocationLimit(queryResult.getInt("user_allocation_limit"))
//            .build();
    }
}
