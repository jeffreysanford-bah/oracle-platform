import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { RecordsModule } from './records/records.module';
import { OracleDatabaseService } from './oracle/oracle-database.service';

@Module({
  imports: [RecordsModule],
  controllers: [AppController],
  providers: [AppService, OracleDatabaseService],
})
export class AppModule {}
