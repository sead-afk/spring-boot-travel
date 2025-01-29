const { MongoClient } = require("mongodb");

async function mapDatabaseSchema(uri) {
    const client = new MongoClient(uri);

    try {
        await client.connect();
        console.log("Connected to MongoDB...");

        // Get a list of databases
        const adminDb = client.db().admin();
        const databases = await adminDb.listDatabases();

        for (const dbInfo of databases.databases) {
            const dbName = dbInfo.name;
            if (dbName === "admin" || dbName === "local" || dbName === "config") {
                continue; // Skip system databases
            }

            console.log(`\nDatabase: ${dbName}`);
            const db = client.db(dbName);

            // Get a list of collections
            const collections = await db.listCollections().toArray();

            for (const collection of collections) {
                console.log(`  Collection: ${collection.name}`);
                const collectionData = await db.collection(collection.name).find().limit(1).toArray();

                if (collectionData.length > 0) {
                    console.log("    Sample Document Schema:");
                    console.log(JSON.stringify(collectionData[0], null, 4));
                } else {
                    console.log("    Collection is empty.");
                }
            }
        }
    } catch (error) {
        console.error("Error mapping database schema:", error);
    } finally {
        await client.close();
    }
}

// Replace <MONGODB_URI> with your MongoDB connection URI
const uri = "mongodb://localhost:27017"; // Example URI for local MongoDB
mapDatabaseSchema(uri);
